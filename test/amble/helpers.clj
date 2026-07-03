(ns amble.helpers
  (:require
    [amble.config :refer [server-url]]
    [clojure.spec.alpha :as s]
    [clojure.test :refer [is]]
    [clj-http.client :as http-client]
    [clojure.test :refer [use-fixtures]]))

(defn is-valid?
  [spec a]
  (is (s/valid? spec a)
      (s/explain-str spec a)))

(defn delete-game-afterwards!
  [game-id]
  (use-fixtures
   :each
   (fn [f]
     (f)
     ;; teardown
     (let [response (http-client/delete (str server-url
                                             "/game/"
                                             game-id))]
       (assert (= 200 (:status response)))))))
