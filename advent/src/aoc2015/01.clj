(ns aoc2015.01
  (:require [clojure.java.io :as io]))

(def data (slurp (io/resource "aoc2015/01.txt")))

(def count-if (comp count filter))

(comment
  ;; first
  (- (count-if #{\(} data)
     (count-if #{\)} data))
  ;; second
  (loop [cnt 0
         [[idx v] & xs] (map-indexed vector data)]
    (if (= cnt -1)
      idx
      (recur (if (= \( v) (inc cnt) (dec cnt)) xs))))
