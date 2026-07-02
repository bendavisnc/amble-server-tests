(ns amble.specs
  (:require
    [clojure.spec.alpha :as s]))

(s/def ::game-id string?)
;; (s/def ::game-id boolean?)

(s/def ::game
  (s/keys :req-un [::game-id]))

