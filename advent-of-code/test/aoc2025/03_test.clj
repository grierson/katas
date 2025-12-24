(ns aoc2025.03-test
  (:require
   [clojure.test :refer [deftest is]]
   [aoc2025.03 :as subject]))

(deftest bank->coll-test
  (is (= [9 8 7 6 5 4 3 2 1 1 1 1 1 1 1]
         (subject/bank->coll "987654321111111"))))
