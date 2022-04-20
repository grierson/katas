(ns aoc2021.11-test
  (:require [clojure.test :refer :all])
  (:require [aoc2021.11 :refer :all]))

; Each step increase by 1
; > 9 causes flash
; - Adjacent octo inc by 1
; - Reset to 0

; INIT
; 9 9 9
; 9 1 9
; 9 9 9

; 1st step
; 10 9 9
; 9  1 9
; 9  9 9

; 2nd step
; 10 10 9
; 9  1 9
; 9  9 9

; ...

; Last step
; 10 10 10
; 10  2 10
; 10 10 10

(defn step [grid]
  grid)


(deftest step-test
  (is (= [[10 9 9]
          [9 1 1]
          [9 9 9]]
         (step [[9 9 9]
                [9 1 9]
                [9 9 9]]))))
