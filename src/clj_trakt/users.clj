(ns ^{:doc "http://docs.trakt.apiary.io/reference/users"}
    clj-trakt.users
  (:require [clj-trakt.core :as trakt]))

(defn settings
  "Get the user's settings."
  [credentials]
  (trakt/request :get credentials [:users :settings]))

(defn requests
  "List a user's pending follow requests."
  [credentials]
  (trakt/request :get credentials [:users :requests]))

(defn approve-request
  "Approve a follower using the id of the request."
  [credentials ^Number id]
  (trakt/request :post credentials [:users :requests id]))

(defn deny-request
  "Deny a follower using the id of the request."
  [credentials ^Number id]
  (trakt/request :delete credentials [:users :requests id]))

(defn hidden
  "Get hidden items for a section."
  [credentials ^clojure.lang.Keyword section & [^clojure.lang.Keyword media]]
  (trakt/request :get credentials [:users :hidden section]
                 {:query-params {:type (name media)}}))

(defn likes
  "Get items a user likes."
  ([credentials]
   (likes credentials nil))
  ([credentials ^clojure.lang.Keyword media]
   (trakt/request :get credentials [:users :likes media])))

(defn profile
  "Get a user's profile information."
  [credentials username]
  (trakt/request :get credentials [:users username]))

(defn collection
  "Get all collected items in a user's collection."
  [credentials username ^clojure.lang.Keyword media
   & [^clojure.lang.Keyword metadata]]
  (trakt/request :get credentials [:users username :collection media]
                 {:query-params {:extended (name metadata)}}))

(defn comments
  "Returns comments a user has posted sorted by most recent."
  ([credentials username ^clojure.lang.Keyword comment_type]
   (comments credentials username ^clojure.lang.Keyword comment_type nil))
  ([credentials username
    ^clojure.lang.Keyword comment_type
    ^clojure.lang.Keyword media]
   (trakt/request :get credentials
                  [:users username :comments comment_type media])))

(defn lists
  "Returns all custom lists for a user."
  ([credentials username]
   (lists credentials username nil))
  ([credentials username id]
   (trakt/request :get credentials [:users username :lists id])))

(defn add-list
  "Create a new custom list. The name is the only required field."
  [credentials username
   {:keys [^String name
           ^String description
           ^String privacy
           ^Boolean display_numbers
           ^Boolean allow_comments]
    :as data}]
  (trakt/request :post credentials [:users username :lists] {:body data}))

(defn update-list
  "Update a custom list by sending 1 or more parameters."
  [credentials username id
   {:keys [^String name
           ^String description
           ^String privacy
           ^Boolean display_numbers
           ^Boolean allow_comments]
    :as data}]
  (trakt/request :put credentials [:users username :lists id] {:body data}))

(defn delete-list
  "Remove a custom list and all items it contains."
  [credentials username id]
  (trakt/request :delete credentials [:users username :lists id]))

(defn like-list
  "Only one like is allowed per list per user."
  [credentials username id]
  (trakt/request :post credentials [:users username :lists id :like]))

(defn unlike-list
  "Remove a like on a list."
  [credentials username id]
  (trakt/request :delete credentials [:users username :lists id :like]))

(defn list-items
  "Get all items on a custom list."
  [credentials username id]
  (trakt/request :get credentials [:users username :lists id :items]))

(defn add-list-items
  "Add one or more items to a custom list."
  [credentials username id data]
  (trakt/request :post credentials [:users username :lists id :items]
                 {:body data}))

(defn remove-list-items
  "Remove one or more items from a custom list."
  [credentials username id data]
  (trakt/request :post credentials [:users username :lists id :items :remove]
                 {:body data}))

(defn list-comments
  "Returns all top level comments for a list."
  [credentials username id]
  (trakt/request :get credentials [:users username :lists id :comments]))

(defn follow
  "If the user has a private profile, the follow request will require approval."
  [credentials username]
  (trakt/request :post credentials [:users username :follow]))

(defn unfollow
  "Unfollow someone you already follow."
  [credentials username]
  (trakt/request :delete credentials [:users username :follow]))

(defn followers
  "Returns all followers including when the relationship began."
  [credentials username]
  (trakt/request :get credentials [:users username :followers]))

(defn following
  "Returns all user's they follow including when the relationship began."
  [credentials username]
  (trakt/request :get credentials [:users username :following]))

(defn friends
  "Returns all friends for a user including when the relationship began."
  [credentials username]
  (trakt/request :get credentials [:users username :friends]))

(defn history
  "Returns movies and episodes that a user has watched, sorted by most recent."
  ([credentials username]
   (history credentials username nil nil))
  ([credentials username ^clojure.lang.Keyword media]
   (history credentials username media nil))
  ([credentials username ^clojure.lang.Keyword media ^Number id
    & [^Number limit]]
   (trakt/request :get credentials [:users username :history media id limit]
                  {:query-params {:limit limit}})))

(defn ratings
  "Get a user's ratings filtered by type."
  ([credentials username]
   (ratings credentials username nil nil))
  ([credentials username ^clojure.lang.Keyword media & [ratings]]
   (trakt/request :get credentials [:users username :ratings media
                                    (->> (if (vector? ratings)
                                           ratings
                                           (vector ratings))
                                         (interpose ",")
                                         (apply str))])))

(defn watchlist
  "Returns all items in a user's watchlist filtered by type."
  ([credentials username]
   (watchlist credentials username nil))
  ([credentials username ^clojure.lang.Keyword media]
   (trakt/request :get credentials [:users username :watchlist media])))

(defn watching
  "Returns a movie or episode if the user is currently watching something."
  [credentials username]
  (trakt/request :get credentials [:users username :watching]))

(defn watched
  "Returns all movies or shows a user has watched sorted by most plays."
  [credentials username ^clojure.lang.Keyword media & [^Boolean noseasons?]]
  (trakt/request :get credentials [:users username :watched media]
                 {:query-params {:extended (when noseasons?
                                             "noseasons")}}))

(defn stats
  "Returns stats about the movies, shows, and episodes for a user."
  [credentials username]
  (trakt/request :get credentials [:users username :stats]))
