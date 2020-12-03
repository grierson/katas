(ns aoc2020.03
  (:require [advent.core :refer [read-file
                                 count-if]]))

(def data (read-file "aoc2020/03.txt"))

(count-if #{\#} (map (fn [line n] (nth (cycle line) n)) data (iterate #(+ 3 %) 0)))

