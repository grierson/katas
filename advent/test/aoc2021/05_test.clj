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
