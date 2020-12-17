(ns aoc2016.01-test
  (:require [midje.sweet :refer :all]
            [aoc2016.01 :refer [solve]]))

(fact "samples for part one"
  (solve [\N [0 0]] [[\R 2] [\L 3]]) => 5
  (solve [\N [0 0]] [[\R 2] [\R 2] [\R 2]]) => 2
  (solve [\N [0 0]] [[\R 5] [\L 5] [\R 5] [\R 3]]) => 12)

