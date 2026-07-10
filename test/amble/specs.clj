(ns amble.specs
  (:require
    [clojure.spec.alpha :as s]))

(s/def ::game-id string?)

(s/def ::game
  (s/keys :req-un [::game-id]))

(s/def ::move-id string?)

(s/def ::move-ids (s/* ::move-id))


(s/def ::move-ids (s/* ::move-id))

(s/def ::x number?)

(s/def ::y number?)

(s/def ::client-id string?)

(s/def ::coord (s/tuple number? number?))

(s/def ::move (s/* ::coord))

(s/def ::player-piece-index (set (range 10)))

(s/def ::id string?)

(s/def ::player
  #{"player-one" "player-two" "player-three" "player-four" "player-five"
    "player-six"})

(s/def ::player-id ::player)

(s/def ::move-with-context
  (s/keys :req-un
          [::game-id
           ::x
           ::y
           ::client-id
           ::move
           ::player-id
           ::player-piece-index
           ::id]))

(s/def ::players (s/* ::player))

(s/def ::player-position (s/coll-of ::coord :count 10))
