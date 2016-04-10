(ns ^{:doc "http://docs.trakt.apiary.io/reference/authentication-devices"}
    clj-trakt.auth
  (:require [clj-trakt.core :as trakt]
            [org.httpkit.client :as http]
            [clojure.data.json :as json]
            [overtone.at-at :as at-at]))

(def job-pool (at-at/mk-pool))
(defonce token-poll (atom nil))

(defn device-code
  [credentials]
  (trakt/request :post credentials [:oauth :device :code]
                 {:body {:client_id (:client_id credentials)}}))

(defn refresh-token
  [credentials]
  (trakt/request :post credentials [:oauth :token]
                 {:body
                  {:refresh_token (:refresh_token credentials)
                   :client_id (:client_id credentials)
                   :client_secret (:client_secret credentials)
                   :redirect_uri "urn:ietf:wg:oauth:2.0:oob"
                   :grant_type "refresh_token"}}))

(defn handle-token
  [response]
  (let [status (condp = (:status response)
                 200 :authorized
                 400 :pending
                 404 :invalid
                 409 :approved
                 410 :expired
                 418 :denied
                 429 :polling
                 :error)]
    (case status
      :authorized (do (println "Authenticated!")
                      (prn (json/read-str (:body response) :key-fn keyword)))
      :pending nil
      (println "ERROR handling token" response))
    (while (not= status :pending)
      (at-at/stop @token-poll)
      (reset! token-poll nil))))

(defn token
  [credentials ^String device-code]
  (trakt/request :post credentials [:oauth :device :token]
                 {:body {:code device-code
                         :client_id (:client_id credentials)
                         :client_secret (:client_secret credentials)}}
                 handle-token))

(defn revoke
  [credentials]
  (trakt/request :post credentials [:oauth :revoke]
                 {:body {:access_token (:access_token credentials)}}))

(defn authenticate
  [credentials]
  (let [response (device-code credentials)]
    (when-not (= :error response)
      (println (str "Use code \"" (:user_code response) "\" at: "
                    (:verification_url response)))
      (println "waiting...")
      (reset! token-poll
              (at-at/every (* 1000 (:interval response))
                           #(token credentials (:device_code response))
                           job-pool)))))
