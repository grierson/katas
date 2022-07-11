(ns aoc2017.01-test
  (:require
   [aoc2017.01 :refer [make-next-pair make-middle-pair solve solve2]]
   [clojure.test :refer [are deftest is]]))

(deftest make-next-pairs-test
  (is (= [[1 1] [1 2] [2 2] [2 1]]
         (make-next-pair "1122")))
  (is (= [[1 1] [1 1] [1 1] [1 1]]
         (make-next-pair "1111"))))

(deftest part1-test
  (are [input expected]
       (= expected (solve input))
    "1122" 3
    "1111" 4
    "1234" 0
    "91212129" 9))

(deftest make-middle-pairs-test
  (is (= [[1 2] [2 1] [2 1] [1 2]]
         (make-middle-pair "1221")))
  (is (= [[1 4] [2 2] [3 5] [4 1] [2 2] [5 3]]
         (make-middle-pair "123425"))))

(deftest part2-test
  (are [input expected]
       (= expected (solve2 input))
    "1212" 6
    "1221" 0
    "123425" 4
    "123123" 12
    "12131415" 4))
