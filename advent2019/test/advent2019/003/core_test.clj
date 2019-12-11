(ns advent2019.003.core_test
  (:require [midje.sweet :refer :all]
            [advent2019.003.core :refer :all]))


(fact "simple case"
      (intersection ..wire1.. ..wire2..) => [1 1]
      (provided
       (draw ..wire1..) => [[1 0] [1 1]]
       (draw ..wire2..) => [[0 1] [1 1]]
       (overlaps [[1 0] [1 1]] [[0 1] [1 1]]) => [[1 1]]
       (nearest [[1 1]]) => [1 1]))
