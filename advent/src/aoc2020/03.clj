(ns aoc2020.03
  (:require [advent.core :refer [read-file
                                 count-if]]))

(def data (read-file "aoc2020/03.txt"))

(defn location [x y] (nth (cycle x) y))
(def count-trees #(count-if #{\#} %))

(let [a (count-trees (map location data (iterate #(+ 1 %) 0)))
      b (count-trees (map location data (iterate #(+ 3 %) 0)))
      c (count-trees (map location data (iterate #(+ 5 %) 0)))
      d (count-trees (map location data (iterate #(+ 7 %) 0)))
      e (count-trees (map location (take-nth 2 data) (iterate #(+ 1 %) 0)))]
  (* a b c d e))
