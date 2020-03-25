(ns rover.rotate-test
  (:require [midje.sweet :refer :all]
            [rover.core :refer :all]))


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

