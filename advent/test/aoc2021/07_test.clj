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
  (testing "sample"
    (is (= 37
           (execute sample)))))


(deftest fuel2-test
  (testing "samples"
    (is (= 66
           (fuel2 16 5)))
    (is (= 10
           (fuel2 1 5)))
    (is (= 6
           (fuel2 2 5)))
    (is (= 15
           (fuel2 0 5)))))

(deftest plan2-test
  (testing "sample"
    (is (= 168
           (plan2 sample 5))))
  (testing "original sample with fuel 2"
    (is (= 206
           (plan2 sample 2)))))

(deftest execute2-test
  (testing "sample"
    (is (= 168
           (execute2 sample)))))
