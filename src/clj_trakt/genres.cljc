(ns clj-trakt.genres
  "http://docs.trakt.apiary.io/reference/genres"
  (:require [clj-trakt.core :as trakt]))

(defn genres
  "Get a list of all genres, including names and slugs."
  [credentials ^clojure.lang.Keyword media]
  (trakt/request :get credentials [:genres media]))
