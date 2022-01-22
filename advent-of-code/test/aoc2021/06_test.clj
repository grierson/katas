(ns aoc2021.06-test
  (:require [clojure.test :refer :all])
  (:require [aoc2021.06 :refer :all]))

(deftest update-state-test
  (testing "reduce days"
    (are [expectedTomorrow today]
      (= expectedTomorrow (update-state today 0))
      [5] [6]
      [3] [4]
      [2] [3]
      [1] [2]
      [0] [1]))

  (testing "Many fish"
    (is (= [0 3]
           (update-state [1 3] 0))))

  (testing "spawn"
    (is (= [6 8]
           (update-state [0] 0))))

  (testing "Spawn when many fish"
    (is (= [2 6 8]
           (update-state [2 0] 1)))))

(deftest calculate-test
  (testing "sample"
    (testing "each step"
      (is (= day1
             (calculate sample 1)))
      (is (= day2
             (calculate day1 1)))
      (is (= day3
             (calculate day2 1))))

    (let [eighteen-days (calculate sample 18)]
      (testing "sample expected"
        (is (= [6 0 6 4 5 6 0 1 1 2 6 0 1 1 1 2 2 3 3 4 6 7 8 8 8 8
                eighteen-days]))
        (is (= 26 (count eighteen-days)))))))

