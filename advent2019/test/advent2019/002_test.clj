(ns advent2019.002-test
  (:require [midje.sweet :refer :all]
            [advent2019.002 :refer :all]))

(future-fact "Examples"
  (foo "1,0,0,99") => "2,0,0,0,99")

(fact "First op should be plus"
  (foo "1,0,0,99") => 0)



