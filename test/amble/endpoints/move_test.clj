(ns amble.endpoints.move-test
  (:require [amble.helpers :refer [is-valid?]]
            [amble.specs :as amble-specs]
            [clj-http.client :as http-client]
            [clojure.test :refer :all]))

(def game-id "TheDummyGame")

(def player-one-first-move-coords
  [[0.525 0.7165] [0.5 0.6732]])

(def player-two-first-move-coords
  [[0.475 0.2835] [0.5 0.3268]])


(deftest get-moves
  (testing "get moves"
    (let [_ (http-client/post "http://localhost:3000/game"
                              {:form-params  {:game-id game-id}
                               :content-type :json})

          _ (doseq [[player first-move-coords i] (map
                                                  vector
                                                  [:player-one :player-two]
                                                  [player-one-first-move-coords
                                                   player-two-first-move-coords]
                                                  [0 1])]
              (http-client/post
               (format "http://localhost:3000/game/%s/player/%s/move/%s"
                       game-id
                       (name player)
                       0)
               {:form-params  {:game-id   game-id
                               :move      first-move-coords
                               :x         (-> first-move-coords
                                              last
                                              first)
                               :y         (-> first-move-coords
                                              last
                                              last)
                               :client-id (str "dummyclientid" i)}
                :content-type :json}))

          response (http-client/get (format "http://localhost:3000/game/%s/move"
                                            game-id)
                                    {:content-type :json
                                     :as :auto})]
      (is (= 200
             (:status response)))
      (is-valid? ::amble-specs/move-ids
                 (:body response))
      (is (= ["0" "1"]
             (:body response)))))

  (testing "get move"
    (let [response (http-client/get (format
                                     "http://localhost:3000/game/%s/move/%s"
                                     game-id
                                     0)
                                    {:content-type :json
                                     :as :auto})]
      (is (= 200
             (:status response)))
      (is-valid? ::amble-specs/move
                 (:body response))))

  (testing "delete move"
    (let [response (http-client/delete (format
                                        "http://localhost:3000/game/%s/move/%s"
                                        game-id
                                        0)
                                       {:content-type :json
                                        :as :auto})]
      (is (= 200
             (:status response))))))

(use-fixtures
 :each
 (fn [f]
   (f)
   ;; teardown
   (let [response (http-client/delete (str "http://localhost:3000/game/"
                                           game-id))]
     (assert (= 200 (:status response))))))


