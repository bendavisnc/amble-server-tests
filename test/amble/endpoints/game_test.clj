(ns amble.endpoints.game-test
  (:require [amble.helpers :refer [is-valid?]]
            [amble.specs :as amble-specs]
            [clj-http.client :as http-client]
            [clojure.test :refer :all]))

(def game-id "TheDummyGame")

(deftest game-endpoints
  (testing "create game"
    (let [response (http-client/post "http://localhost:3000/game"
                                     {:form-params {:game-id game-id}
                                      :content-type :json
                                      :as :auto})]
      (is (= 201
             (:status response)))
      (is-valid? ::amble-specs/game
                 (:body response))))

  (testing "get game, not found"
    (let [response (http-client/get (str "http://localhost:3000/game/"
                                         (str game-id 2))
                                    {:throw-exceptions false})]
      (is (= 404
             (:status response)))))

  (testing "get game"
    (let [response (http-client/get (str "http://localhost:3000/game/"
                                         game-id)
                                    {:content-type :json
                                     :as :auto})]
      (is (= 200
             (:status response)))
      (is-valid? ::amble-specs/game
                 (:body response)))))

(use-fixtures
 :each
 (fn [f]
   (f)
   ;; teardown
   (let [response (http-client/delete (str "http://localhost:3000/game/"
                                           game-id))]
     (assert (= 200 (:status response))))))


