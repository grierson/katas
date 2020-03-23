(ns rover.core-test
  (:require [midje.sweet :refer :all]
            [rover.core :refer :all]))

(tabular
  (fact "Degenerate test cases"
    (move start-point ?input) => start-point)
  ?input
  nil
  ""
  "A"
  "AB")

(tabular
  (fact "Rotate right once"
    (move {:direction ?current} ?input) => {:direction ?expected})
  ?current ?input ?expected
  :N "R" :E
  :E "R" :S
  :S "R" :W
  :W "R" :N)

(tabular
  (fact "Rotate right multiple time"
    (move {:direction :N} ?input) => {:direction ?expected})
  ?input ?expected
  "R" :E
  "RR" :S
  "RRR" :W
  "RRRR" :N)

(tabular
  (fact "rotate-right"
    (rotate-right {:direction ?current}) => {:direction ?expected})
  ?current ?expected
  :N :E
  :E :S
  :S :W
  :W :N)

(tabular
  (fact "rotate-left"
    (rotate-left {:direction ?current}) => {:direction ?expected})
  ?current ?expected
  :N :W
  :E :N
  :S :E
  :W :S)

(tabular
  (fact "Rotate left once"
    (move {:direction ?current} ?input) => {:direction ?expected})
  ?current ?input ?expected
  :N "L" :W
  :E "L" :N
  :S "L" :E
  :W "L" :S)

(tabular
  (fact "Rotate left multiple times"
    (move {:direction :N} ?input) => {:direction ?expected})
  ?input ?expected
  "L" :W
  "LL" :S
  "LLL" :E
  "LLLL" :N)
