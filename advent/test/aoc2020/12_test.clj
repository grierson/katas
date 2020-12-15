(ns aoc2020.12-test
  (:require [midje.sweet :refer :all]
            [aoc2020.12 :refer [move
                                north
                                forward]]))

(facts "sample"
  (facts "step 1"
    (move [:E [0 0]] ["F" 10]) => [:E [10 0]])
  (facts "step 2"
    (move [:E [10 0]] ["N" 3]) => [:E [10 3]])
  (facts "step 3"
    (move [:E [10 3]] ["F" 7]) => [:E [17 3]])
  (facts "step 4"
    (move [:E [17 3]] ["R" 90]) => [:S [17 3]])
  (facts "step 5"
    (move [:S [17 3]] ["F" 11]) => [:S [17 -8]]))


(fact "forward"
  (forward [:N [0 0]] 10) => [:N [0 10]]
  (forward [:E [0 0]] 10) => [:E [10 0]]
  (forward [:S [0 0]] 10) => [:S [0 -10]]
  (forward [:W [0 0]] 10) => [:W [-10 0]])

(fact "north"
  (north [:N [0 0]] 10) => [:N [0 10]])
