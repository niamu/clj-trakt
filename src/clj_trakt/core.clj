(ns clj-trakt.core
  (:require [org.httpkit.client :as http]
            [clojure.data.json :as json]))

(defn date
  "Construct Trakt date format yyyy-MM-dd"
  [[^Number year ^Number month ^Number day]]
  (apply str (interpose "-" [year month day])))

(defn url
  [^String domain & [path]]
  (str domain
       (->> path
            (remove #(or (nil? %) (= "" %)))
            (map #(if (keyword? %) (name %) %))
            (interpose "/" )
            (apply str))))

(defn- handle
  [response]
  (condp = (:status response)
    200 (json/read-str (:body response) :key-fn keyword)
    (throw (Exception. response))))

(defn request
  ([^clojure.lang.Keyword method credentials path]
   (request method credentials path nil handle))
  ([^clojure.lang.Keyword method credentials path data]
   (request method credentials path data handle))
  ([^clojure.lang.Keyword method credentials path data handler-fn]
   (let [headers {"Content-Type" "application/json"}
         options {:headers
                  (if (:access_token credentials)
                    (merge headers
                           {"Authorization" (str "Bearer "
                                                 (:access_token credentials))
                            "trakt-api-key" (:client_id credentials)
                            "trakt-api-version" 2})
                    headers)}]
     (-> ((condp = method
            :get http/get
            :post http/post
            :delete http/delete
            :put http/put
            http/get)
          (url (:api-url credentials) path)
          (merge (if (contains? data :body)
                   (assoc data :body (json/write-str (:body data)))
                   data)
                 options))
         deref
         handler-fn))))
