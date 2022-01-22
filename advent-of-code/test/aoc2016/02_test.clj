(ns aoc2016.02-test
  (:require [clojure.test :refer :all]
            [aoc2016.02 :refer [solve get-number]]))

(deftest get-number-test
  (are [actual expected]
    (= actual expected)
    (get-number 5 "U") 2
    (get-number 5 "D") 8
    (get-number 5 "L") 4
    (get-number 5 "R") 6
    (get-number 5 "UU") 2
    (get-number 5 "DD") 8))

(deftest sample-steps-test
  (are [actual expected]
    (= actual expected)
    (get-number 5 "ULL") 1
    (get-number 1 "RRDDD") 9
    (get-number 9 "LURDL") 8
    (get-number 8 "UUUD") 5))

(deftest sample-test
  (is (= (solve ["ULL" "RRDDD" "LURDL" "UUUD"]) [1 9 8 5])))
