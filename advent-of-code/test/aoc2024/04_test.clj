(ns aoc2024.04-test
  (:require
   [aoc2024.04 :refer [parse directions find-all-x sample surrounding xmas? count-surrounding-mas]]
   [clojure.test :refer [deftest is testing]]))

(deftest surrounding-test
  (is (= directions (surrounding [0 0]))))

(deftest find-all-x-test
  (is (= [[0 4]
          [0 5]
          [1 4]
          [2 2]
          [2 4]
          [3 9]
          [4 0]
          [4 6]
          [5 0]
          [5 1]
          [5 5]
          [5 6]
          [6 7]
          [7 2]
          [8 5]
          [9 1]
          [9 3]
          [9 5]
          [9 9]]
         (find-all-x (parse sample)))))

(deftest xmas?-test
  (is (= false
         (xmas? [\S \A \M])))
  (is (= true
         (xmas? [\M \A \S]))))

(deftest count-surrounding-mas-test
  (testing "Seventh X with 0"
    (is (= 0
           (count-surrounding-mas (parse sample) [6 0]))))
  (testing "First X with 1 south east"
    (is (= 1
           (count-surrounding-mas (parse sample) [0 4]))))
  (testing "Second X with 1 east"
    (is (= 1
           (count-surrounding-mas (parse sample) [0 5]))))
  (testing "Last X with 2, north and north west"
    (is (= 2
           (count-surrounding-mas (parse sample) [9 9])))))
