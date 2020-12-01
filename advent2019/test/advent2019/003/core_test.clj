(ns advent2019.003.core_test
  (:require [midje.sweet :refer :all]
            [advent2019.003.core :refer :all]))

(fact "intersection at bottom right [1 1] provided"
      (solve ..wire1.. ..wire2..) => [1 1]
      (provided
       (trace ..wire1..) => [[1 0] [1 1]]
       (trace ..wire2..) => [[0 1] [1 1]]
       (overlaps [[1 0] [1 1]] [[0 1] [1 1]]) => #{[1 1]}
       (nearest #{[1 1]}) => [1 1]))

(fact "draw provided"
      (trace "R1,D1") => [[1 0] [1 1]]
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
                    (parse "R1") => '([\R 1]))
              (fact "L1"
                    (parse "L1") => '([\L 1]))
              (fact "U1"
                    (parse "U1") => '([\U 1]))
              (fact "D1"
                    (parse "D1") => '([\D 1]))
              (fact "R1,L1"
                    (parse "R1,L1") => '([\R 1] [\L 1]))
              (fact "R10,D10"
                    (parse "R10,D10") => '([\R 10] [\D 10]))))

(facts "mapping"
       (facts "one move"
              (fact "right 1"
                    (mapping [[\R 1]]) => [[0 0][1 0]])
              (fact "left 1"
                    (mapping [[\L 1]]) => [[0 0] [-1 0]])
              (fact "up 1"
                    (mapping [[\U 1]]) => [[0 0] [0 -1]])
              (fact "down 1"
                    (mapping [[\D 1]]) => [[0 0] [0 1]]))
       (facts "two moves"
              (fact "right 1 up 1"
                    (mapping [[\R 1] [\U 1]]) => [[0 0] [1 0] [1 -1]])))

(facts "overlaps"
       (fact "one overlap"
             (overlaps [[0 0] [1 0] [1 1]] [[0 0] [0 1] [1 1]]) => #{[0 0] [1 1]}))

(facts "distance"
       (fact "[1 1]"
             (distance [1 1]) => 2))

(fact "simple case"
      (solve "R1,U1" "U1,R1") => 2)

(fact "first example"
      (solve "R75,D30,R83,U83,L12,D49,R71,U7,L72"
             "U62,R66,U55,R34,D71,R55,D58,R83") => 159)

(fact "second example"
      (solve "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51"
             "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7") => 135)
