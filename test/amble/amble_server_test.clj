(ns amble.amble-server-test
  (:require [clojure.test :refer :all]
            [clj-http.client :as http-client]))

(deftest test-amble-server-api
  (testing "ping"
    (let [response (http-client/get "http://localhost:3000/ping")
          result   (:body response)]
      (is (= result
             "Hello World")))))