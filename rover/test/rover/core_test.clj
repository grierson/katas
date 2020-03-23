(ns rover.core-test
  (:require [midje.sweet :refer :all]
            [rover.core :refer :all]))

(fact "Rotate right"
  (move {:direction :N} "R") => {:direction :E})
