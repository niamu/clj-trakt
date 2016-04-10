# clj-trakt

`clj-trakt` is a Clojure library to interface with the [Trakt](http://trakt.tv) API.

## Documentation

[Trakt API documention](http://docs.trakt.apiary.io).

`clj-trakt` has nearly complete coverage of the Trakt API with the exception of all of the Oauth authentication methods (only the [Devices](http://docs.trakt.apiary.io/reference/authentication-devices) authentication methods are implemented so far).

There is very generous usage of Clojure type hinting throughout the source code that will hopefully aid anyone that might be having difficulties in what functions expect for arguments and data structures.

## Usage

Add the following dependency to your `project.clj` file:

```
[clj-trakt "1.0.0]
```

## Example

To begin using `clj-trakt` in your project you will first need to generate a valid access token. [Create a Trakt App](https://trakt.tv/oauth/applications) if you haven't already.

```clojure
(ns example.core
  (:require [clj-trakt.auth :as trakt]))

;; client_id and client_secret can be found in your application page within Trakt.
(def credentials
  {:api-url "https://api-v2launch.trakt.tv/"
   :client_id "39a6af3627ac61c416fd3ebd8da45e7b000df534f55faa0d7336dc6e99c2e915"
   :client_secret "2797825b6b820708d5d401bc1fd7ae6b18bd6de401d292025ea40a049d03f640"}

(defn create-token
  [trakt-app]
  (trakt/authenticate trakt-app))

(println (create-token credentials))
#_{:access_token "cabb7da92563814b43082388c4a6cc8a6c54b6b3fd8550ed9d43f63ba85b8322"
   :token_type "bearer"
   :expires_in 7776000
   :refresh_token "d6bc38e266bdd85634611cad0e1d4b29ce3ceb1464137c9d26bd69d2d6b1eee0"
   :scope "public"}
```

Once you have generated yourself a valid access-token map, you are free to manage it however you wish, although it probably makes the most sense for the access-token to reside within an atom that can be updated as needed to handle refreshing of the tokens.

```clojure
(defonce token
  (atom {:access_token "cabb7da92563814b43082388c4a6cc8a6c54b6b3fd8550ed9d43f63ba85b8322"
         :token_type "bearer"
         :expires_in 7776000
         :refresh_token "d6bc38e266bdd85634611cad0e1d4b29ce3ceb1464137c9d26bd69d2d6b1eee0"
         :scope "public"}))

;; Now you can refresh the access-token by doing something like this...
(reset! token (trakt/refresh-token @token))
```

The rest is rather simple from here. Just require the namespaces you need for your API interactions and provide the function with the merged `credentials` and `token` for valid authentication.

```clojure
(ns example.core
  (:require [clj-trakt.users :as trakt-users])

(def credentials {...})
(defonce token (atom {...}))

(println (trakt-users/profile (merge credentials @token) "niamu"))
```

## License

Copyright © 2016 Brendon Walsh

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
