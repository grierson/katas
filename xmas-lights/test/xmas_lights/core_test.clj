(ns xmas-lights.core-test
  (:require [midje.sweet :refer :all]
            [xmas-lights.core :refer :all]))

(fact "make grid"
  (make-grid) => []
  (make-grid 1 1) => [[false]]
  (make-grid 2 2) => [[false false]
                      [false false]]
  (make-grid 2 1) => [[false]
                      [false]]
  (make-grid 1 2) => [[false false]])
