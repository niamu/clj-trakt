(ns ^{:doc "http://docs.trakt.apiary.io/reference/shows"}
    clj-trakt.shows
  (:require [clj-trakt.core :as trakt]))

(defn trending
  "Returns all shows being watched right now."
  [credentials & [^Number limit]]
  (trakt/request :get credentials [:shows :trending]
                 {:query-params {:limit limit}}))

(defn popular
  "Returns the most popular shows."
  [credentials & [^Number limit]]
  (trakt/request :get credentials [:shows :popular]
                 {:query-params {:limit limit}}))

(defn played
  "Returns the most played shows in the specified time period."
  ([credentials]
   (played credentials nil))
  ([credentials ^clojure.lang.Keyword period & [^Number limit]]
   (trakt/request :get credentials [:shows :played period]
                  {:query-params {:limit limit}})))

(defn watched
  "Returns the most watched shows in the specified period."
  ([credentials]
   (played credentials nil))
  ([credentials ^clojure.lang.Keyword period & [^Number limit]]
   (trakt/request :get credentials [:shows :watched period]
                  {:query-params {:limit limit}})))

(defn collected
  "Returns the most collected shows in the specified time period."
  ([credentials]
   (played credentials nil))
  ([credentials ^clojure.lang.Keyword period & [^Number limit]]
   (trakt/request :get credentials [:shows :collected period]
                  {:query-params {:limit limit}})))

(defn anticipated
  "Returns the most anticipated shows."
  [credentials & [^Number limit]]
  (trakt/request :get credentials [:shows :anticipated]
                 {:query-params {:limit limit}}))

(defn updates
  "Returns all shows updated since the specified date."
  [credentials [^Number year ^Number month ^Number day] & [^Number limit]]
  (trakt/request :get credentials [:shows :updates
                                   (trakt/date [year month day])]
                 {:query-params {:limit limit}}))

(defn summary
  "Returns a single shows's details."
  [credentials id]
  (trakt/request :get credentials [:shows id]))

(defn aliases
  "Returns all title aliases for a show."
  [credentials id]
  (trakt/request :get credentials [:shows id :aliases]))

(defn translations
  "Returns all translations for a show."
  [credentials id language]
  (trakt/request :get credentials [:shows id :translations language]))

(defn comments
  "Returns all top level comments for a show."
  [credentials id & [^Number limit]]
  (trakt/request :get credentials [:shows id :comments]
                 {:query-params {:limit limit}}))

(defn collection-progress
  "Returns collection progress for show."
  [credentials id & [^Boolean hidden? ^Boolean specials? ^Number limit]]
  (trakt/request :get credentials [:shows id :progress :collection]
                 {:query-params {:limit limit
                                 :hidden hidden?
                                 :specials specials?}}))

(defn watched-progress
  "Returns watched progress for show."
  [credentials id & [^Boolean hidden? ^Boolean specials? ^Number limit]]
  (trakt/request :get credentials [:shows id :progress :watched]
                 {:query-params {:limit limit
                                 :hidden hidden?
                                 :specials specials?}}))

(defn people
  "Returns all cast and crew for a show."
  [credentials id]
  (trakt/request :get credentials [:shows id :people]))

(defn ratings
  "Returns rating (between 0 and 10) and distribution for a show."
  [credentials id]
  (trakt/request :get credentials [:shows id :ratings]))

(defn related
  "Returns related and similar shows."
  [credentials id & [^Number limit]]
  (trakt/request :get credentials [:shows id :related]
                 {:query-params {:limit limit}}))

(defn stats
  "Returns lots of show stats."
  [credentials id]
  (trakt/request :get credentials [:shows id :stats]))

(defn watching
  "Returns all users watching this show right now."
  [credentials id]
  (trakt/request :get credentials [:shows id :watching]))
