(ns aoc2020.03
  (:require [advent.core :refer [count-if]]
            [clojure.java.io :as io]
            [clojure.string :as str]))

(def data (str/split-lines (slurp (io/resource "aoc2020/03.txt"))))

(defn location [x y] (nth (cycle x) y))
(def count-trees #(count-if #{\#} %))

(defn foo [coll step]
  (count-trees (map location coll (iterate #(+ step %) 0))))

(time
  (let [a (foo data 1)
        b (foo data 3)
        c (foo data 5)
        d (foo data 7)
        e (foo (take-nth 2 data) 1)]
    (* a b c d e)))
