(ns amble.helpers
  (:require
    [clojure.spec.alpha :as s]
    [clojure.test :refer [is]]))

(defn is-valid?
  [spec a]
  (is (s/valid? spec a)
      (s/explain-str spec a)))

