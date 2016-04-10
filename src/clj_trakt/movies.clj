(ns ^{:doc "http://docs.trakt.apiary.io/reference/movies"}
    clj-trakt.movies
  (:require [clj-trakt.core :as trakt]))

(defn trending
  "Returns all movies being watched right now."
  [credentials & [^Number limit]]
  (trakt/request :get credentials [:movies :trending]
                 {:query-params {:limit limit}}))

(defn popular
  "Returns the most popular movies."
  [credentials & [^Number limit]]
  (trakt/request :get credentials [:movies :popular]
                 {:query-params {:limit limit}}))

(defn played
  "Returns the most played movies in the specified time period."
  ([credentials]
   (played credentials nil))
  ([credentials ^clojure.lang.Keyword period & [^Number limit]]
   (trakt/request :get credentials [:movies :played period]
                  {:query-params {:limit limit}})))

(defn watched
  "Returns the most watched movies in the specified time period."
  ([credentials]
   (played credentials nil))
  ([credentials ^clojure.lang.Keyword period & [^Number limit]]
   (trakt/request :get credentials [:movies :watched period]
                  {:query-params {:limit limit}})))

(defn collected
  "Returns the most collected movies in the specified time period."
  ([credentials]
   (played credentials nil))
  ([credentials ^clojure.lang.Keyword period & [^Number limit]]
   (trakt/request :get credentials [:movies :collected period]
                  {:query-params {:limit limit}})))

(defn anticipated
  "Returns the most anticipated movies."
  [credentials & [^Number limit]]
  (trakt/request :get credentials [:movies :anticipated]
                 {:query-params {:limit limit}}))

(defn box-office
  "Returns the top 10 grossing movies in the U.S. box office last weekend."
  [credentials]
  (trakt/request :get credentials [:movies :boxoffice]))

(defn updates
  "Returns all movies updated since the specified date."
  [credentials [^Number year ^Number month ^Number day] & [^Number limit]]
  (trakt/request :get credentials [:movies :updates
                                   (trakt/date [year month day])]
                 {:query-params {:limit limit}}))

(defn summary
  "Returns a single movie's details."
  [credentials id]
  (trakt/request :get credentials [:movies id]))

(defn aliases
  "Returns all title aliases for a movie."
  [credentials id]
  (trakt/request :get credentials [:movies id :aliases]))

(defn releases
  "Returns all releases for a movie."
  [credentials id country]
  (trakt/request :get credentials [:movies id :releases country]))

(defn translations
  "Returns all translations for a movie."
  [credentials id language]
  (trakt/request :get credentials [:movies id :translations language]))

(defn comments
  "Returns all top level comments for a movie."
  [credentials id & [^Number limit]]
  (trakt/request :get credentials [:movies id :comments]
                 {:query-params {:limit limit}}))

(defn people
  "Returns all cast and crew for a movie."
  [credentials id]
  (trakt/request :get credentials [:movies id :people]))

(defn ratings
  "Returns rating (between 0 and 10) and distribution for a movie."
  [credentials id]
  (trakt/request :get credentials [:movies id :ratings]))

(defn related
  "Returns related and similar movies."
  [credentials id & [^Number limit]]
  (trakt/request :get credentials [:movies id :related]
                 {:query-params {:limit limit}}))

(defn stats
  "Returns lots of movie stats."
  [credentials id]
  (trakt/request :get credentials [:movies id :stats]))

(defn watching
  "Returns all users watching this movie right now."
  [credentials id]
  (trakt/request :get credentials [:movies id :watching]))
