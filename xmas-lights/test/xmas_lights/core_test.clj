(ns xmas-lights.core-test
  (:require [midje.sweet :refer :all]
            [xmas-lights.core :refer :all]))

(fact "make grid"
  (make-grid 1 1) => [[false]]
  (make-grid 2 2) => [[false false]
                      [false false]]
  (make-grid 2 1) => [[false false]]
  (make-grid 1 2) => [[false] [false]])

(facts "switch lights"
  (fact "turn on light"
    (let [grid (make-grid 1 1)]
      (turn-on grid [0 0]) => [[true]]))

  (fact "turn off light"
    (let [grid (turn-on (make-grid 1 1) [0 0])]
      (turn-off grid [0 0]) => [[false]])))

(fact "toggle lights"
  (fact "toggle light on"
    (let [grid (make-grid 1 1)]
      (toggle grid [0 0]) => [[true]]))

  (fact "toggle light off"
    (let [grid (turn-on (make-grid 1 1) [0 0])]
      (toggle grid [0 0]) => [[false]])))

(fact "Get range"
  (get-range [2 2] [4 4]) => [[2 2] [2 3] [2 4]
                              [3 2] [3 3] [3 4]
                              [4 2] [4 3] [4 4]])

(fact "Turn on many"
  (many turn-on (make-grid 3 3) [0 0] [1 1]) => [[true true false]
                                                 [true true false]
                                                 [false false false]])

(fact "Turn off many"
  (let [on-grid (many turn-on (make-grid 3 3) [0 0] [1 1])]
    (many turn-off on-grid [0 0] [1 1]) => [[false false false]
                                            [false false false]
                                            [false false false]]))

(fact "Toggle many"
  (let [grid [[true false true]
              [false true false]
              [true false true]]]
    (many toggle grid [0 0] [2 2]) => [[false true false]
                                       [true false true]
                                       [false true false]]))
