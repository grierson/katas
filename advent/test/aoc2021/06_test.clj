(ns aoc2021.06-test
  (:require [clojure.test :refer :all])
  (:require [aoc2021.06 :refer :all]))

(deftest calculate-test
  (testing "reduce days"
    (are [expectedTomorrow today]
      (= expectedTomorrow (calculate today 1))
      [5] [6]
      [3] [4]
      [2] [3]
      [1] [2]
      [0] [1]))

  (testing "Many fish"
    (is (= [0 2]
           (calculate [1 3] 1))))

  (testing "spawn"
    (is (= [6 8]
           (calculate [0] 1))))

  (testing "Spawn when many fish"
    (is (= [1 6 8]
           (calculate [2 0] 1))))

  (testing "sample"
    (testing "each step"
      (is (= day1
             (calculate sample 1)))
      (is (= day2
             (calculate day1 1)))
      (is (= day3
             (calculate day2 1))))

    (testing "3 days"
      (is (= day3
             (calculate sample 3))))))

