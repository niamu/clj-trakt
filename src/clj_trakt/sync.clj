(ns ^{:doc "http://docs.trakt.apiary.io/reference/sync"}
    clj-trakt.sync
  (:require [clj-trakt.core :as trakt]))

(defn last-activities
  "Recent activities by date. Cache these locally to compare with future syncs."
  [credentials]
  (trakt/request :get credentials [:sync :last_activities]))

(defn playback
  "Use this progress to sync up playback across different media centers."
  ([credentials]
   playback credentials nil)
  ([credentials ^clojure.lang.Keyword media]
   (trakt/request :get credentials [:sync :playback media])))

(defn remove-playback
  "Remove a playback item from a user's playback progress list."
  [credentials id]
  (trakt/request :delete credentials [:sync :playback id]))

(defn collection
  [credentials ^clojure.lang.Keyword media & [^clojure.lang.Keyword metadata]]
  (trakt/request :get credentials [:sync :collection media]
                 {:query-params {:extended (name metadata)}}))

(defn add-to-collection
  "Add items to a user's collection."
  [credentials data]
  (trakt/request :post credentials [:sync :collection] {:body data}))

(defn remove-from-collection
  "Remove one or more items from a user's collection."
  [credentials data]
  (trakt/request :post credentials [:sync :collection :remove] {:body data}))

(defn watched
  "Returns all movies or shows a user has watched sorted by most plays."
  [credentials media & [^Boolean noseasons?]]
  (trakt/request :get credentials [:sync :watched media]
                 {:query-params {:extended (when noseasons?
                                             "noseasons")}}))
(defn history
  "Returns movies and episodes that a user has watched, sorted by most recent."
  ([credentials]
   (history credentials nil nil))
  ([credentials ^clojure.lang.Keyword media]
   (history credentials ^clojure.lang.Keyword media nil))
  ([credentials ^clojure.lang.Keyword media id & [^Number limit]]
   (trakt/request :get credentials [:sync :history media id]
                  {:query-params {:limit limit}})))

(defn add-to-history
  "Add items to a user's watch history."
  [credentials data]
  (trakt/request :post credentials [:sync :history] {:body data}))

(defn remove-from-history
  "Remove items from a user's watch history."
  [credentials data]
  (trakt/request :post credentials [:sync :history :remove] {:body data}))

(defn ratings
  "Get a user's ratings filtered by type."
  ([credentials]
   (ratings credentials nil nil))
  ([credentials username ^clojure.lang.Keyword media & [ratings]]
   (trakt/request :get credentials [:sync :ratings media
                                    (->> (if (vector? ratings)
                                           ratings
                                           (vector ratings))
                                         (interpose ",")
                                         (apply str))])))

(defn add-ratings
  "Rate one or more items."
  [credentials data]
  (trakt/request :post credentials [:sync :ratings] {:body data}))

(defn remove-ratings
  "Remove ratings for one or more items."
  [credentials data]
  (trakt/request :post credentials [:sync :ratings :remove] {:body data}))

(defn watchlist
  "Returns all items in a user's watchlist filtered by type."
  ([credentials]
   (watchlist credentials nil))
  ([credentials ^clojure.lang.Keyword media]
   (trakt/request :get credentials [:sync :watchlist media])))

(defn add-to-watchlist
  "Add one of more items to a user's watchlist."
  [credentials data]
  (trakt/request :post credentials [:sync :watchlist] {:body data}))

(defn remove-from-watchlist
  "Remove one or more items from a user's watchlist."
  [credentials data]
  (trakt/request :post credentials [:sync :watchlist :remove] {:body data}))
