(ns rover.play-test
  (:require [midje.sweet :refer :all]
            [rover.core :refer :all]))

(tabular
  (fact "Degenerate test cases"
    (play start-point ?input) => start-point)
  ?input
  nil
  ""
  "A"
  "AA")

(tabular
  (fact "Rotate right once"
    (play {:direction ?current} ?input) => {:direction ?expected})
  ?current ?input ?expected
  :N "R" :E
  :E "R" :S
  :S "R" :W
  :W "R" :N)

(tabular
  (fact "Rotate right multiple time"
    (play {:direction :N} ?input) => {:direction ?expected})
  ?input ?expected
  "R" :E
  "RR" :S
  "RRR" :W
  "RRRR" :N)

(tabular
  (fact "Rotate left once"
    (play {:direction ?current} ?input) => {:direction ?expected})
  ?current ?input ?expected
  :N "L" :W
  :E "L" :N
  :S "L" :E
  :W "L" :S)

(tabular
  (fact "Rotate left multiple times"
    (play {:direction :N} ?input) => {:direction ?expected})
  ?input ?expected
  "L" :W
  "LL" :S
  "LLL" :E
  "LLLL" :N)


(fact "Forward, right, and forward"
  (play {:direction :N :x 0 :y 0} "FRF") => {:direction :E
                                             :x         1
                                             :y         1})

(fact "Edge of grid"
  (play {:direction :N :x 10 :y 0} "RF") => {:direction :E
                                             :x         0
                                             :y         0})
