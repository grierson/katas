(ns aoc2021.07-test
  (:require [clojure.test :refer :all])
  (:require [aoc2021.07 :refer :all]))


(deftest fuel-test
  (is (= 0
         (fuel 0 0)))
  (is (= 1
         (fuel 1 0)))
  (is (= 2
         (fuel 2 0)))
  (is (= 14
         (fuel 16 2))))

(deftest plan-test
  (testing "sample"
    (testing "cheapest possible outcome"
      (is (= 37
             (plan sample 2))))
    (testing "more expensive outcomes"
      (is (= 41
             (plan sample 1)))
      (is (= 39
             (plan sample 3)))
      (is (= 71
             (plan sample 10))))))


(deftest execute-test
  (is (= 37
         (execute sample))))