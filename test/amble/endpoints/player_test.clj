(ns amble.endpoints.player-test
  (:require [amble.helpers :refer [is-valid?]]
            [amble.specs :as amble-specs]
            [clj-http.client :as http-client]
            [clojure.test :refer :all]))

(def game-id "TheDummyGame")

(deftest player-endpoints
  (testing "get players"
    (let [_ (http-client/post "http://localhost:3000/game"
                              {:form-params  {:game-id game-id}
                               :content-type :json})

          response (http-client/get (format
                                     "http://localhost:3000/game/%s/player"
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
                     "http://localhost:3000/game/%s/player/player-one"
                     game-id)
                    {:content-type :json
                     :as :auto})]
      (is (= 200
             (:status response)))
      ;; (is (= 2
      ;;  (:body response))))))

      (is-valid? ::amble-specs/player-position
                 (:body response)))))


(use-fixtures
 :each
 (fn [f]
   (f)
   ;; teardown
   (let [response (http-client/delete (str "http://localhost:3000/game/"
                                           game-id))]
     (assert (= 200 (:status response))))))


