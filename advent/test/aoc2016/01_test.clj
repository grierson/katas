(ns aoc2016.01-test
  (:require [midje.sweet :refer :all]
            [aoc2016.01 :refer [solve
                                solve2]]))


(fact "samples for part one"
  (solve [\N [0 0]] [[\R 2] [\L 3]]) => 5
  (solve [\N [0 0]] [[\R 2] [\R 2] [\R 2]]) => 2
  (solve [\N [0 0]] [[\R 5] [\L 5] [\R 5] [\R 3]]) => 12)

#_(future-fact "samples for part two"
    (solve2 #{}  [\N [0 0]] [[\R 8] [\R 4] [\R 4] [\R 8]]) => 4)
