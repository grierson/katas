(ns aoc2017.02
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(def sample "5 1 9 5\n7 5 3\n2 4 6 8")

(defn parse-line [line]
  (map #(Integer/parseInt %) (str/split line #"\t")))

(defn diff [nums]
  (let [low (apply min nums)
        high (apply max nums)]
    (- high low)))

(defn solve [lines]
  (reduce + (map diff lines)))

(comment
  (solve
    (map parse-line (str/split-lines (slurp (io/resource "aoc2017/02.txt"))))))