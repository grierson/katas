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
      (turn-on [0 0] grid)) => [[true]])

  (fact "turn off light"
    (let [grid (turn-on [0 0] (make-grid 1 1))]
      (turn-off [0 0] grid)) => [[false]]))

(fact "toggle lights"
  (fact "toggle light on"
    (let [grid (make-grid 1 1)]
      (toggle [0 0] grid)) => [[true]])

  (fact "toggle light off"
    (let [grid (turn-on [0 0] (make-grid 1 1))]
      (toggle [0 0] grid)) => [[false]]))

(fact "Get range"
  (get-range [2 2] [4 4]) => [[2 2] [2 3] [2 4]
                              [3 2] [3 3] [3 4]
                              [4 2] [4 3] [4 4]])

(fact "Turn on many"
  (many turn-on [0 0] [1 1] (make-grid 3 3)) => [[true true false]
                                                 [true true false]
                                                 [false false false]])

(fact "Turn off many"
  (let [on-grid (many turn-on [0 0] [1 1] (make-grid 3 3))]
    (many turn-off [0 0] [1 1] on-grid)) => [[false false false]
                                             [false false false]
                                             [false false false]])

(fact "Toggle many"
  (let [grid [[true false true]
              [false true false]
              [true false true]]]
    (many toggle [0 0] [2 2] grid)) => [[false true false]
                                        [true false true]
                                        [false true false]])
(fact "Amount on"
  (amount-on (make-grid 1 1)) => 0
  (amount-on (many turn-on [0 0] [1 1] (make-grid 3 3))) => 4)
