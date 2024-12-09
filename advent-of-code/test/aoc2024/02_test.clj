(ns aoc2024.02-test
  (:require [clojure.test :refer [deftest is testing are]]
            [aoc2024.02 :refer [ordered? small? permutations]]))

(deftest ordered?-test
  (are [input expected]
       (= (ordered? input) expected)
    '(7 6 4 2 1) true
    '(1 2 7 8 9) true
    '(9 7 6 2 1) true
    '(1 3 2 4 5) false
    '(8 6 4 4 1) false
    '(1 3 6 7 9) true))

(deftest small?-test
  (are [input expected]
       (= (small? input) expected)
    '(7 6 4 2 1) true
    '(1 2 7 8 9) false
    '(9 7 6 2 1) false
    '(1 3 2 4 5) true
    '(8 6 4 4 1) false
    '(1 3 6 7 9) true))

(deftest permutations-test
  (testing "First sample"
    (is (= [[6 4 2 1]
            [7 4 2 1]
            [7 6 2 1]
            [7 6 4 1]
            [7 6 4 2]]
           (permutations [7 6 4 2 1])))))
