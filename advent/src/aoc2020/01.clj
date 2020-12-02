(ns aoc2020.01
  (:require [aoc.common :refer [read-file]]))

(def data (read-file "aoc2020/01.txt"))
(def nums (map #(Long/parseLong %) data))

(first (for [x nums
             y nums
             :when (= 2020 (+ x y))]
         (* x y)))

(first (for [x nums
             y nums
             z nums
             :when (= 2020 (+ x y z))]
         (* x y z)))
