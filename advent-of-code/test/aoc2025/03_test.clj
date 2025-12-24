(ns aoc2025.03-test
  (:require
   [clojure.test :refer [deftest is]]
   [aoc2025.03 :as subject]))

(deftest bank->coll-test
  (is (= [9 8 7 6 5 4 3 2 1 1 1 1 1 1 1]
         (subject/bank->coll "987654321111111"))))

(deftest find-indexes-test
  (is (= [0]
         (subject/find-indexes [9 8 7 6 5 4 3 2 1 1 1 1 1 1 1] 9)))
  (is (= [1]
         (subject/find-indexes [9 8 7 6 5 4 3 2 1 1 1 1 1 1 1] 8)))
  (is (= [8 9 10 11 12 13 14]
         (subject/find-indexes [9 8 7 6 5 4 3 2 1 1 1 1 1 1 1] 1))))
