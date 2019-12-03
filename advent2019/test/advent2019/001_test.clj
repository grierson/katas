(ns advent2019.001-test
  (:require [midje.sweet :refer :all]
            [advent2019.001 :refer :all]))

(tabular
  (fact "Examples"
    (fuel ?mass) => ?expected)
    [?mass ?expected]
    12 2
    14 2
    1969 654
    100756 33583)

(tabular
  (fact "Silly cases"
    (fuel ?value) => 0)
    [?value]
    0
    -1
    1
    2
    3
    4
    5)

(tabular
  (fact "Simple cases"
    (fuel ?value) => ?expected)
    [?value ?expected]
    9 1)


