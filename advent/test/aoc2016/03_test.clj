(ns aoc2016.03-test
  (:require [midje.sweet :refer :all]
            [aoc2016.03 :refer [valid?]]))

(fact "invalid"
  (valid? [1 1 3]) => false
  (valid? [5 10 25]) => false)

(fact "valid"
  (valid? [1 1 1]) => true)
