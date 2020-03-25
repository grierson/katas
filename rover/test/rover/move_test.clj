(ns rover.move-test
  (:require [midje.sweet :refer :all]
            [rover.core :refer :all]))


(facts "move-forward"
  (fact "north"
    (move-forward {:direction :N
                   :y         0}) => (contains {:y 1}))
  (fact "south"
    (move-forward {:direction :S
                   :y         1}) => (contains {:y 0}))
  (fact "east"
    (move-forward {:direction :E
                   :x         0}) => (contains {:x 1}))
  (fact "west"
    (move-forward {:direction :W
                   :x         1}) => (contains {:x 0})))

(facts "move-backward"
  (fact "north"
    (move-backward {:direction :N
                    :y         1}) => (contains {:y 0}))
  (fact "south"
    (move-backward {:direction :S
                    :y         0}) => (contains {:y 1}))
  (fact "east"
    (move-backward {:direction :E
                    :x         1}) => (contains {:x 0}))
  (fact "west"
    (move-backward {:direction :W
                    :x         0}) => (contains {:x 1})))


(facts "handle edge of grid"
  (facts "Top of grid"
    (fact "facing north and move forward"
      (move-forward {:direction :N
                     :y         10}) => (contains {:y 0}))
    (fact "facing south and move backward"
      (move-backward {:direction :S
                      :y         10}) => (contains {:y 0})))
  (facts "Bottom of grid"
    (fact "facing north and move backward"
      (move-backward {:direction :N
                      :y         0}) => (contains {:y 10}))
    (fact "Bottom of grid facing south and move forward"
      (move-forward {:direction :S
                     :y         0}) => (contains {:y 10})))
  (facts "Right of grid"
    (fact "facing east and move forward"
      (move-forward {:direction :E
                     :x         10}) => (contains {:x 0}))
    (fact "facing west and move backwards"
      (move-backward {:direction :W
                      :x         10}) => (contains {:x 0})))
  (facts "Left of grid"
    (fact "facing west and move forward"
      (move-backward {:direction :W
                      :x         10}) => (contains {:x 0}))
    (fact "facing east and move backward"
      (move-backward {:direction :E
                      :x         0}) => (contains {:x 10}))))
