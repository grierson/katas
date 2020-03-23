(ns rover.core-test
  (:require [midje.sweet :refer :all]
            [rover.core :refer :all]))

(tabular
  (fact "Rotate right"
    (move {:direction ?current} ?input) => {:direction ?expected})
  ?current ?input ?expected
  :N "R" :E
  :E "R" :W)
