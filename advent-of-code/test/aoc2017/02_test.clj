(ns aoc2017.02-test
  (:require
   [aoc2017.02 :refer [make-divisible-pair make-low-high-pair parse-line
                       solve1 solve2]]
   [clojure.test :refer [deftest is]]))

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

(deftest make-divisable-pair-test
  (is (= [8 2]
         (make-divisible-pair [5 9 2 8])))
  (is (= [9 3]
         (make-divisible-pair [9 4 7 3])))
  (is (= [6 3]
         (make-divisible-pair [3 8 6 5]))))

(def sample2 "5\t9\t2\t8\n9\t4\t7\t3\t\n3\t8\t6\t5")

(deftest sample2-test
  (is (= 9
         (solve2 sample2))))
