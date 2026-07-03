(ns amble.endpoints.player-test
  (:require [amble.helpers :refer [is-valid? delete-game-afterwards!]]
            [amble.specs :as amble-specs]
            [amble.config :refer [server-url]]
            [clj-http.client :as http-client]
            [clojure.test :refer :all]))

(def game-id "TheDummyGame")

(deftest player-endpoints
  (testing "get players"
    (let [_ (http-client/post (str server-url "/game")
                              {:form-params  {:game-id game-id}
                               :content-type :json})

          response (http-client/get (format
                                     "%s/game/%s/player"
                                     server-url
                                     game-id)
                                    {:content-type :json
                                     :as :auto})]
      (is (= 200
             (:status response)))
      (is-valid? ::amble-specs/players
                 (:body response))))

  (testing "get player"
    (let [response (http-client/get
                    (format
                     "%s/game/%s/player/player-one"
                     server-url
                     game-id)
                    {:content-type :json
                     :as :auto})]
      (is (= 200
             (:status response)))
      ;; (is (= 2
      ;;  (:body response))))))

      (is-valid? ::amble-specs/player-position
                 (:body response)))))


(delete-game-afterwards! game-id)