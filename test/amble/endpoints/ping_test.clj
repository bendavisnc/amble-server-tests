(ns amble.endpoints.ping-test
  (:require [clojure.test :refer :all]
            [clj-http.client :as http-client]))

(deftest get-ping
  (testing "get-ping"
    (let [response (http-client/get "http://localhost:3000/ping")
          result   (:body response)]
      (is (= 200
             (:status response)))
      (is (= result
             "Hello World")))))
