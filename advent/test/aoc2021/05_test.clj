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
  (testing "draw row"
    (is (= [[1 1] [2 1] [3 1]]
           (draw-line [[1 1] [3 1]]))))
  (is (= [[9 7] [8 7] [7 7]]
         (draw-line [[9 7] [7 7]]))))
