(ns advent2019.003.core_test
  (:require [midje.sweet :refer :all]
            [advent2019.003.core :refer :all]))

(fact "intersection at bottom right [1 1] provided"
      (intersection ..wire1.. ..wire2..) => [1 1]
      (provided
       (draw ..wire1..) => [[1 0] [1 1]]
       (draw ..wire2..) => [[0 1] [1 1]]
       (overlaps [[1 0] [1 1]] [[0 1] [1 1]]) => [[1 1]]
       (nearest [[1 1]]) => [1 1]))

(fact "draw provided"
      (draw "R1,D1") => [[1 0] [1 1]]
      (provided
       (parse "R1,D1") => ..parsed..
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

(facts "mapping"
       (facts "one move"
              (fact "right 1"
                    (mapping [[:right 1]]) => [[0 0][1 0]])
              (fact "left 1"
                    (mapping [[:left 1]]) => [[0 0] [-1 0]])
              (fact "up 1"
                    (mapping [[:up 1]]) => [[0 0] [0 -1]])
              (fact "down 1"
                    (mapping [[:down 1]]) => [[0 0] [0 1]]))
       (facts "two moves"
              (fact "right 1 up 1"
                    (mapping [[:right 1] [:up 1]]) => [[0 0] [1 0] [1 -1]])))
