(ns aoc2024.03
  (:require
   [clojure.java.io :as io]))

(def sample "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))")

(def input (slurp (io/resource "aoc2024/03.txt")))

(defn parse [data]
  (let [multis (re-seq #"mul\((\d+),(\d+)\)" data)
        pairs (map (fn [[_ multiplier multiplicand]] [(parse-long multiplier) (parse-long multiplicand)]) multis)]
    pairs))

(defn solve1 [multis]
  (reduce + (map (fn [[multiplier multiplicand]] (* multiplier multiplicand)) multis)))

(comment
  (solve1 (parse sample))
  (solve1 (parse input)))
