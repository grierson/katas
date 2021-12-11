(ns aoc2021.05-test
  (:require [clojure.test :refer :all]
            [aoc2021.05 :refer :all]))


(deftest data->line-test
  (is (= [[0 9] [5 9]]
         (data->line "0,9 -> 5,9")))
  (is (= [[11 22] [33 44]]
         (data->line "11,22 -> 33,44"))))


(deftest data->lines-test
  (is (= [[[0 9] [5 9]]
          [[8 0] [0 8]]]
         (data->lines ["0,9 -> 5,9"
                       "8,0 -> 0,8"]))))

(deftest draw-line-test
  (testing "draw column"
    (is (= [[1 1] [1 2] [1 3]]
           (draw-line [[1 1] [1 3]]))))
  (testing "draw column when starting point is larger"
    (is (= [[1 3] [1 2] [1 1]]
           (draw-line [[1 3] [1 1]]))))
  (testing "draw row"
    (is (= [[1 1] [2 1] [3 1]]
           (draw-line [[1 1] [3 1]]))))
  (testing "draw row when starting point is big"
    (is (= [[3 1] [2 1] [1 1]]
           (draw-line [[3 1] [1 1]]))))
  (testing "draw example"
    (is (= [[1 1] [1 2] [1 3]]
           (draw-line [[1 1] [1 3]])))
    (is (= [[9 7] [8 7] [7 7]]
           (draw-line [[9 7] [7 7]])))))

(deftest log-line-test
  (testing "add points to log"
    (is (= {[1 1] 1}
           (log-line {} [[1 1]]))))
  (testing "increment point in log"
    (is (= {[1 1] 2}
           (log-line {[1 1] 1} [[1 1]])))))

(deftest diagonal?-test
  (is (true? (diagonal? [[0 0] [1 1]]))))