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

(future-facts "draw wires"
       (fact "R1,U1"
             (draw "R1,U1") => [[1 0] [1 1]])
       (fact "U1,R1"
             (draw "U1,R1") => [[0 1] [1 1]]))

(fact "R1,U1 provided"
      (draw "R1,U1") => [[1 0] [1 1]]
      (provided
       (parse "R1,U1") => ..parsed..
       (mapping ..parsed..) => [[1 0] [1 1]]))

(fact "Parse"
      (fact "R1"
            (parse "R1") => [[:right 1]]
            (provided
             (str->move "R1") => [:right 1])))
