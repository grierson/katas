(ns advent2019.002.core_test
  (:require [midje.sweet :refer :all]
            [advent2019.002.core :refer :all]))

(facts "Silly"
  (fact "empty"
    (intcode "") => 0
    (intcode nil) => 0)
  (fact "less than 4 numbers"
    (intcode "0") => 0
    (intcode "0,1") => 0
    (intcode "0,1,2") => 0)
  (fact "operation out of index"
    (intcode "3,0,0,0") => 0))
