(ns xmas-lights.core-test
  (:require [midje.sweet :refer :all]
            [xmas-lights.core :refer :all]))

(fact "make grid"
  (make-grid 1 1) => [[false]]
  (make-grid 2 2) => [[false false]
                      [false false]]
  (make-grid 2 1) => [[false false]]
  (make-grid 1 2) => [[false] [false]])

(fact "turn on light"
  (let [grid (make-grid 1 1)]
    (turn-on grid [0 0]) => [[true]]))


(fact "Get range"
  (get-range [2 2] [4 4]) => [[2 2] [2 3] [2 4]
                              [3 2] [3 3] [3 4]
                              [4 2] [4 3] [4 4]])

(fact "Turn on many"
  (turn-on-many (make-grid 3 3) [0 0] [1 1]) => [[true true false]
                                                 [true true false]
                                                 [false false false]])
