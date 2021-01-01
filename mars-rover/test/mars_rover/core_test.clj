(ns mars-rover.core-test
  (:require [midje.sweet :refer :all]
            [mars-rover.core :refer :all]))

(facts "rotate"
  (tabular
    (fact "right"
      (play {:direction ?direction} "R") => {:direction ?new-direction})
    ?direction ?new-direction
    :N :E
    :E :S
    :S :W
    :W :N)
  (tabular
    (fact "left"
      (play {:direction ?direction} "L") => {:direction ?new-direction})
    ?direction ?new-direction
    :N :W
    :E :N
    :S :E
    :W :S))

(facts "move"
  (fact "forward"
    (play (make-rover) "F") => (contains {:y 1}))
  (fact "backward (set rover up one in order to avoid wrapping"
    (play (->Rover 0 1 :N) "B") => (contains {:y 0})))

(facts "wrap"
  (fact "y axis"
    (fact "top"
      (play (->Rover 0 10 :N) "F") => (contains {:y 0}))
    (fact "top - backwards"
      (play (->Rover 0 10 :S) "B") => (contains {:y 0}))
    (fact "bottom"
      (play (->Rover 0 0 :S) "F") => (contains {:y 10}))
    (fact "bottom - backwards"
      (play (->Rover 0 0 :N) "B") => (contains {:y 10})))
  (fact "x axis"
    (fact "right"
      (play (->Rover 10 0 :E) "F") => (contains {:x 0}))
    (fact "right - backwards"
      (play (->Rover 10 0 :W) "B") => (contains {:x 0}))
    (fact "left"
      (play (->Rover 0 0 :W) "F") => (contains {:x 10}))
    (fact "left - backwards"
      (play (->Rover 0 0 :E) "B") => (contains {:x 10}))))

(facts "examples"
  (play (make-rover) "F") => (->Rover 0 1 :N)
  (play (make-rover) "FF") => (->Rover 0 2 :N)
  (play (make-rover) "FRF") => (->Rover 1 1 :E))
