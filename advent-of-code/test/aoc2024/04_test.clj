(ns aoc2024.04-test
  (:require
   [aoc2024.04 :refer [parse
                       mas-directions
                       find-all
                       sample
                       mas-surrounding
                       mas?
                       count-surrounding-mas]]
   [clojure.test :refer [deftest is testing]]))

(deftest surrounding-test
  (is (= mas-directions (mas-surrounding [0 0]))))

(deftest find-all-x-test
  (testing "find x"
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
           (find-all \X (parse sample)))))
  (testing "find a"
    (is (= [[0 7]
            [1 2]
            [1 9]
            [2 0]
            [2 6]
            [2 7]
            [3 2]
            [3 4]
            [4 2]
            [4 4]
            [4 7]
            [5 2]
            [5 7]
            [5 9]
            [6 5]
            [7 1]
            [7 3]
            [7 5]
            [7 7]
            [7 8]
            [7 9]
            [8 1]
            [9 4]
            [9 7]]
           (find-all \A (parse sample))))))

(deftest xmas?-test
  (is (= false
         (mas? [\S \A \M])))
  (is (= true
         (mas? [\M \A \S]))))

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
