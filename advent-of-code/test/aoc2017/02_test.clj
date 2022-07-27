(ns aoc2017.02-test
  (:require
   [aoc2017.02 :refer [solve1 make-low-high-pair parse-line]]
   [clojure.test :refer [are deftest is]]))

(def sample1 "5\t1\t9\t5\n7\t5\t3\n2\t4\t6\t8")

(deftest parse-line-test
  (is (= [5 1 9 5]
         (parse-line "5\t1\t9\t5")))
  (is (= [7 5 3]
         (parse-line "7\t5\t3")))
  (is (= [2 4 6 8]
         (parse-line "2\t4\t6\t8"))))

(deftest row->pair-test
  (is (= [1 9]
         (make-low-high-pair [5 1 9 5])))
  (is (= [3 7]
         (make-low-high-pair [7 5 3])))
  (is (= [2 8]
         (make-low-high-pair [2 4 6 8]))))

(deftest sample1-test
  (is (= 18
         (solve1 sample1))))
