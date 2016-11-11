(ns clj-trakt.calendars
  "http://docs.trakt.apiary.io/reference/calendars"
  (:require [clj-trakt.core :as trakt]))

(defn my-shows
  "Returns all shows airing during the time period specified."
  [credentials [^Number year ^Number month ^Number day] ^Number days]
  (trakt/request :get credentials [:calendars :my :shows
                                   (trakt/date [year month day])
                                   days]))

(defn my-new-shows
  "Returns all new show premieres airing during the time period specified."
  [credentials [^Number year ^Number month ^Number day] ^Number days]
  (trakt/request :get credentials [:calendars :my :shows :new
                                   (trakt/date [year month day])
                                   days]))

(defn my-season-premieres
  "Returns all show season premieres airing during the time period specified."
  [credentials [^Number year ^Number month ^Number day] ^Number days]
  (trakt/request :get credentials [:calendars :my :shows :premieres
                                   (trakt/date [year month day])
                                   days]))

(defn my-movies
  "Returns all movies with a release date during the time period specified."
  [credentials [^Number year ^Number month ^Number day] ^Number days]
  (trakt/request :get credentials [:calendars :my :movies
                                   (trakt/date [year month day])
                                   days]))

(defn all-shows
  "Returns all shows airing during the time period specified."
  [credentials [^Number year ^Number month ^Number day] ^Number days]
  (trakt/request :get credentials [:calendars :all :shows
                                   (trakt/date [year month day])
                                   days]))

(defn all-new-shows
  "Returns all new show premieres airing during the time period specified."
  [credentials [^Number year ^Number month ^Number day] ^Number days]
  (trakt/request :get credentials [:calendars :all :shows :new
                                   (trakt/date [year month day])
                                   days]))

(defn all-season-premieres
  "Returns all show season premieres airing during the time period specified."
  [credentials [^Number year ^Number month ^Number day] ^Number days]
  (trakt/request :get credentials [:calendars :all :shows :premieres
                                   (trakt/date [year month day])
                                   days]))

(defn all-movies
  "Returns all movies with a release date during the time period specified."
  [credentials [^Number year ^Number month ^Number day] ^Number days]
  (trakt/request :get credentials [:calendars :all :movies
                                   (trakt/date [year month day])
                                   days]))
