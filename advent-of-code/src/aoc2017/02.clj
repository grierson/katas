(ns aoc2017.02
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn parse-line [line]
  (map parse-long (str/split line #"\t")))

(defn make-low-high-pair [nums]
  [(apply min nums) (apply max nums)])

(defn make-divisible-pair [nums]
  (defn foo [nums [x & xs]]
    (when x
      (let [a (some (fn [y]
                      (when (not= x y)
                        (cond
                          (int? (/ x y)) [x y]
                          (int? (/ y x)) [y x]))) nums)]
        (if a
          a
          (foo nums xs)))))
  (foo nums (set nums)))

(defn solve-fn [pair-fn total-fn]
  (fn  [lines]
    (->> (str/split-lines lines)
         (map parse-line)
         (map pair-fn)
         (map total-fn)
         (reduce +))))

(def solve1 (solve-fn make-low-high-pair (fn [[low high]] (- high low))))
(def solve2 (solve-fn make-divisible-pair (fn [[high low]] (/ high low))))

(comment
  (solve1 (slurp (io/resource "aoc2017/02.txt")))
  (solve2 (slurp (io/resource "aoc2017/02.txt"))))
