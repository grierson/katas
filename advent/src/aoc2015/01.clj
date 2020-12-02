(ns aoc2015.01
  (:require [aoc.common :as common]))

(def data (first (common/read-file "aoc2015/01.txt")))

(def count-if (comp count filter))

(comment
  ;; first
  (- (count-if #{\(} data)
     (count-if #{\)} data)))

(map-indexed vector data)

(loop [cnt 0
       [[idx v] & xs] (map-indexed vector data)]
  (if (= cnt -1)
    idx
    (recur (if (= \( v) (inc cnt) (dec cnt)) xs)))
