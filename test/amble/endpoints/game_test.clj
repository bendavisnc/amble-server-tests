(ns amble.endpoints.game-test
  (:require [amble.helpers :refer [is-valid? delete-game-afterwards!]]
            [amble.specs :as amble-specs]
            [amble.config :refer [server-url]]
            [clj-http.client :as http-client]
            [clojure.test :refer :all]))

(def game-id "TheDummyGame")

(deftest game-endpoints
  (testing "create game"
    (let [response (http-client/post (str server-url "/game")
                                     {:form-params {:game-id game-id}
                                      :content-type :json
                                      :as :auto})]
      (is (= 201
             (:status response)))
      (is-valid? ::amble-specs/game
                 (:body response))))

  (testing "get game, not found"
    (let [response (http-client/get (str server-url
                                         "/game/"
                                         (str game-id 2))
                                    {:throw-exceptions false})]
      (is (= 404
             (:status response)))))

  (testing "get game"
    (let [response (http-client/get (str server-url
                                         "/game/"
                                         game-id)
                                    {:content-type :json
                                     :as :auto})]
      (is (= 200
             (:status response)))
      (is-valid? ::amble-specs/game
                 (:body response)))))

(delete-game-afterwards! game-id)
