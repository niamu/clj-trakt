(ns clj-trakt.checkin
  "http://docs.trakt.apiary.io/reference/checkin"
  (:require [clj-trakt.core :as trakt]))

(defn checkin
  "Check into a movie or episode."
  [credentials data]
  (trakt/request :post credentials [:checkin] {:body data}))

(defn cancel-checkins
  "Removes any active checkins, no need to provide a specific item."
  [credentials]
  (trakt/request :delete credentials [:checkin]))
