(ns ^{:doc "http://docs.trakt.apiary.io/reference/comments"}
    clj-trakt.comments
  (:require [clj-trakt.core :as trakt]))

(defn add-comment
  "Add a new comment to a movie, show, season, episode, or list."
  [credentials {:keys [^String comment ^Boolean spoiler] :as user-comment}]
  (trakt/request :post credentials [:comments] {:body user-comment}))

(defn comments
  "Returns a single comment and indicates how many replies it has."
  [credentials ^Number id]
  (trakt/request :get credentials [:comments id]))

(defn update-comment
  "Update a single comment created within the last hour."
  [credentials ^Number id {:keys [^String comment ^Boolean spoiler]
                           :as user-comment}]
  (trakt/request :put credentials [:comments id] {:body user-comment}))

(defn remove-comment
  "Delete a single comment created within the last hour."
  [credentials ^Number id]
  (trakt/request :delete credentials [:comments id]))

(defn replies
  "Returns replies on a comment."
  [credentials ^Number id]
  (trakt/request :get credentials [:comments id :replies]))

(defn reply
  "Add a new reply to an existing comment."
  [credentials ^Number id {:keys [^String comment ^Boolean spoiler] :as reply}]
  (trakt/request :post credentials [:comments id :replies] {:body reply}))

(defn like
  "Only one like is allowed per comment per user."
  [credentials ^Number id]
  (trakt/request :post credentials [:comments id :like]))

(defn unlike
  "Remove a like on a comment."
  [credentials ^Number id]
  (trakt/request :delete credentials [:comments id :like]))
