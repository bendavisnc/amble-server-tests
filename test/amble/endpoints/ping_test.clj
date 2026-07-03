(ns amble.endpoints.ping-test
  (:require [clojure.test :refer :all]
            [clj-http.client :as http-client]
            [amble.config :refer [server-url]]))

(deftest get-ping
  (testing "get-ping"
    (let [response (http-client/get (str server-url "/ping"))
          result   (:body response)]
      (is (= 200
             (:status response)))
      (is (= result
             "Hello World")))))
