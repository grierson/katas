(ns aoc2016.02-test
  (:require
   [aoc2016.02 :refer [get-number solve]]
   [clojure.test :refer [are deftest is testing]]))

(deftest get-number-test
  (are [actual expected] (= actual expected)
    (get-number 5 "U") 2
    (get-number 5 "D") 8
    (get-number 5 "L") 4
    (get-number 5 "R") 6
    (get-number 5 "ULL") 1
    (get-number 1 "RRDDD") 9
    (get-number 9 "LURDL") 8
    (get-number 8 "UUUD") 5))

(deftest solve-test
  (is (= (solve ["ULL" "RRDDD" "LURDL" "UUUD"]) "1985")))
