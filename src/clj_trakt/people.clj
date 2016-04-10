(ns ^{:doc "http://docs.trakt.apiary.io/reference/people"}
    clj-trakt.people
  (:require [clj-trakt.core :as trakt]))

(defn summary
  "Returns a single person's details."
  [credentials id]
  (trakt/request :get credentials [:people id]))

(defn movies
  "Returns all movies where this person is in the cast or crew."
  [credentials id]
  (trakt/request :get credentials [:people id :movies]))

(defn shows
  "Returns all shows where this person is in the cast or crew."
  [credentials id]
  (trakt/request :get credentials [:people id :shows]))
