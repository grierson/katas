(ns aoc2017.03)

(take 10 (map #(* 8 %) (iterate inc 1)))

; 1, 9, 25, 49
; 1 + 1 * 8 => 9
; 9 + 2 * 8 => 25
; 25 + 3 * 8 => 49

