(ns rover.move-test
  (:require [midje.sweet :refer :all]
            [rover.core :refer :all]))


(facts "move-forward"
  (fact "north"
    (move-forward {:direction :N
                   :y 0}) => (contains {:y 1}))
  (fact "south"
    (move-forward {:direction :S
                   :y 1}) => (contains {:y 0}))
  (fact "east"
    (move-forward {:direction :E
                   :x 0}) => (contains {:x 1}))
  (fact "west"
    (move-forward {:direction :W
                   :x 1}) => (contains {:x 0})))

(facts "move-backward"
  (fact "north"
    (move-backward {:direction :N
                    :y 1}) => (contains {:y 0}))
  (fact "south"
    (move-backward {:direction :S
                    :y 0}) => (contains {:y 1}))
  (fact "east"
    (move-backward {:direction :E
                    :x 1}) => (contains {:x 0}))
  (fact "west"
    (move-backward {:direction :W
                    :x 0}) => (contains {:x 1})))
