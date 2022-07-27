(ns aoc2017.02
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn parse-line [line]
  (map parse-long (str/split line #"\t")))

(defn make-low-high-pair [nums]
  [(apply min nums) (apply max nums)])

(defn solve1 [lines]
  (->> (str/split-lines lines)
       (map parse-line)
       (map make-low-high-pair)
       (map (fn [[low high]] (- high low)))
       (reduce +)))

(comment
  (solve1 (slurp (io/resource "aoc2017/02.txt"))))
