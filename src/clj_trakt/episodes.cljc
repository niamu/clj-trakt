(ns clj-trakt.episodes
  "http://docs.trakt.apiary.io/reference/episodes"
  (:require [clj-trakt.core :as trakt]))

(defn summary
  "Returns a single episode's details."
  [credentials id ^Number season ^Number episode]
  (trakt/request :get credentials [:shows id
                                   :seasons season
                                   :episodes episode]))

(defn comments
  "Returns all top level comments for an episode."
  [credentials id ^Number season ^Number episode]
  (trakt/request :get credentials [:shows id
                                   :seasons season
                                   :episodes episode
                                   :comments]))

(defn ratings
  "Returns rating (between 0 and 10) and distribution for an episode."
  [credentials id ^Number season ^Number episode]
  (trakt/request :get credentials [:shows id
                                   :seasons season
                                   :episodes episode
                                   :ratings]))

(defn stats
  "Returns lots of episode stats."
  [credentials id ^Number season ^Number episode]
  (trakt/request :get credentials [:shows id
                                   :seasons season
                                   :episodes episode
                                   :stats]))

(defn watching
  "Returns all users watching this episode right now."
  [credentials id ^Number season ^Number episode]
  (trakt/request :get credentials [:shows id
                                   :seasons season
                                   :episodes episode
                                   :watching]))
