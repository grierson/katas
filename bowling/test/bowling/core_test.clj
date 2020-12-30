(ns bowling.core-test
  (:require [midje.sweet :refer :all]
            [bowling.core :refer :all]))

(facts "Bowling game"
  (fact "gutter game"
    (score (repeat 10 [0 0])) => 0)

  (fact "bowl 1 pin each roll"
    (score (repeat 10 [1 1])) => 20)

  (fact "bowl spare"
    (score [[5 5] [0 0]]) => 10
    (score [[5 5] [1 0]]) => 12)

  (fact "bowl strike"
    (score [[10 0] [0 0]]) => 10
    (score [[10 0] [1 1]]) => 14)

  (fact "Suggested test cases"
    (score (conj (vec (repeat 9 [10])) [10 10 10])) => 300
    (score (repeat 10 [9 0])) => 90
    (score (conj (vec (repeat 9 [5 5])) [5 5 5])) => 150))
