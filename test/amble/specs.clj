(ns amble.specs
  (:require
    [clojure.spec.alpha :as s]))

(s/def ::game-id string?)

(s/def ::game
  (s/keys :req-un [::game-id]))

(s/def ::move-id string?)

(s/def ::move-ids (s/* ::move-id))


(s/def ::move-ids (s/* ::move-id))

(s/def ::x string?) ;; todo

(s/def ::y string?)

(s/def ::client-id string?)

(s/def ::coord (s/tuple number? number?))

(s/def :amble.specs.private/move (s/* ::coord))


(s/def ::player-id string?)

(s/def ::player-piece-index string?)

(s/def ::id string?)

(s/def ::move
  (s/keys :req-un
          [::game-id
           ::x
           ::y
           ::client-id
           :amble.specs.private/move
           ::player-id
           ::player-piece-index
           ::id]))

