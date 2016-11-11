(ns clj-trakt.recommendations
  "http://docs.trakt.apiary.io/reference/recommendations"
  (:require [clj-trakt.core :as trakt]))

(defn movies
  "Personalized movie recommendations for a user."
  [credentials & [^Number limit]]
  (trakt/request :get credentials [:recommendations :movies]
                 {:query-params {:limit limit}}))

(defn hide-movie
  "Hide a movie from getting recommended anymore."
  [credentials id]
  (trakt/request :delete credentials [:recommendations :movies id]))

(defn shows
  "Personalized show recommendations for a user."
  [credentials & [^Number limit]]
  (trakt/request :get credentials [:recommendations :shows]
                 {:query-params {:limit limit}}))

(defn hide-show
  "Hide a show from getting recommended anymore."
  [credentials id]
  (trakt/request :delete credentials [:recommendations :shows id]))
