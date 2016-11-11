(ns clj-trakt.search
  "http://docs.trakt.apiary.io/reference/search"
  (:require [clj-trakt.core :as trakt]))

(defn text
  "Perform a text query."
  [credentials {:keys [^String query ^String type ^Number year] :as params}]
  (trakt/request :get credentials [:search] {:query-params params}))

(defn id-lookup
  "Lookup an item by using a Trakt.tv ID or other external ID."
  [credentials {:keys [^String id_type ^String id] :as params}]
  (trakt/request :get credentials [:search] {:query-params params}))
