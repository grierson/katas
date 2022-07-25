(ns aoc2017.01
  (:require [clojure.java.io :as io]))

(defn- char->int [x]
  (parse-long (str x)))

(defn make-next-pair [[a :as number]]
  (partition 2 1
             (map char->int
                  (str number a))))

(defn make-middle-pair [number]
  (let [middle (/ (count number) 2)
        repeating-number (cycle number)]
    (map-indexed
     (fn [i v]
       [(char->int v)
        (char->int (nth repeating-number (+ i middle)))])
     number)))

(defn update-state [state [a b]]
  (if (= a b)
    (+ state a)
    state))

(defn total-matching [pairs]
  (reduce
   update-state
   0
   pairs))

(defn solve-fn [pair-fn]
  (fn [number]
    (total-matching (pair-fn number))))

(def solve (solve-fn make-next-pair))
(def solve2 (solve-fn make-middle-pair))

(comment
  (def input (slurp (io/resource "aoc2017/01.txt")))
  (solve input)
  (solve2 input))
