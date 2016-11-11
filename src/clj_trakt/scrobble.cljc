(ns clj-trakt.scrobble
  "http://docs.trakt.apiary.io/reference/scrobble"
  (:require [clj-trakt.core :as trakt]))

(defn start
  "Use this method when the video intially starts playing or is unpaused."
  [credentials {:keys [movie episode
                       ^Double progress
                       ^String app_version
                       ^String app_date]
                :as data}]
  (trakt/request :post credentials [:scrobble :start] {:body data}))

(defn pause
  "Use this method when the video is paused."
  [credentials {:keys [movie episode
                       ^Double progress
                       ^String app_version
                       ^String app_date]
                :as data}]
  (trakt/request :post credentials [:scrobble :pause] {:body data}))

(defn stop
  "Use this method when the video is stopped or finishes playing on its own."
  [credentials {:keys [movie episode
                       ^Double progress
                       ^String app_version
                       ^String app_date]
                :as data}]
  (trakt/request :post credentials [:scrobble :stop] {:body data}))
