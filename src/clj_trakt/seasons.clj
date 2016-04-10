(ns ^{:doc "http://docs.trakt.apiary.io/reference/seasons"}
    clj-trakt.seasons
  (:require [clj-trakt.core :as trakt]))

(defn summary
  "Returns all seasons for a show."
  [credentials id & [^String extended]]
  (trakt/request :get credentials [:shows id :seasons]
                 {:query-params {:extended extended}}))

(defn season
  "Returns all episodes for a specific season of a show."
  [credentials id ^Number season]
  (trakt/request :get credentials [:shows id :seasons season]))

(defn comments
  "Returns all top level comments for a season."
  [credentials id ^Number season]
  (trakt/request :get credentials [:shows id :seasons season :comments]))

(defn ratings
  "Returns rating (between 0 and 10) and distribution for a season."
  [credentials id ^Number season]
  (trakt/request :get credentials [:shows id :seasons season :ratings]))

(defn stats
  "Returns lots of season stats."
  [credentials id ^Number season]
  (trakt/request :get credentials [:shows id :seasons season :stats]))

(defn watching
  "Returns all users watching this season right now."
  [credentials id ^Number season]
  (trakt/request :get credentials [:shows id :seasons season :watching]))
