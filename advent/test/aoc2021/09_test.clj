(ns aoc2021.09-test
  (:require [clojure.test :refer :all])
  (:require [aoc2021.09 :refer :all]))




(deftest neighbours-test
  (is (= [1 3]
         (neighbours sample [0 0])))
  (is (= [9 9 2]
         (neighbours sample [0 1])))
  (is (= [9 8 1]
         (neighbours sample [0 2])))
  (is (= [2 9 9]
         (neighbours sample [1 0]))))


(deftest isLowest?-test
  (is (true? (isLowest? [9 9 2] 1)))
  (is (false? (isLowest? [1 3] 2))))
