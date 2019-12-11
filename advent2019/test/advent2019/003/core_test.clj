(ns advent2019.003.core_test
  (:require [midje.sweet :refer :all]
            [advent2019.003.core :refer :all]))

(fact "Simple case - intersection [1 1] provided"
      (intersection ..wire1.. ..wire2..) => [1 1]
      (provided
       (draw ..wire1..) => [[1 0] [1 1]]
       (draw ..wire2..) => [[0 1] [1 1]]
       (overlaps [[1 0] [1 1]] [[0 1] [1 1]]) => [[1 1]]
       (nearest [[1 1]]) => [1 1]))

(fact "draw provided"
      (draw "R1,U1") => [[1 0] [1 1]]
      (provided
       (parse "R1,U1") => ..parsed..
       (mapping ..parsed..) => [[1 0] [1 1]]))

(fact "Parse provided"
      (fact "R1"
            (parse "R1") => [[:right 1]]
            (provided
             (str->move "R1") => [:right 1])))

(facts "Parse"
       (facts "Simple"
              (fact "R1"
                    (parse "R1") => '([:right 1]))
              (fact "L1"
                    (parse "L1") => '([:left 1]))
              (fact "U1"
                    (parse "U1") => '([:up 1]))
              (fact "D1"
                    (parse "D1") => '([:down 1]))
              (fact "R1,L1"
                    (parse "R1,L1") => '([:right 1] [:left 1]))
              (fact "R10,D10"
                    (parse "R10,D10") => '([:right 10] [:down 10]))))
