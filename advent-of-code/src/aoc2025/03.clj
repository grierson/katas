(ns aoc2025.03
  (:require
   [clojure.java.io :as io]
   [clojure.string :as s]))

(def sample-data "987654321111111
811111111111119
234234234234278
818181911112111")

(def data (slurp (io/resource "aoc2025/03.txt")))

(defn bank->coll
  "bank string -> [number]"
  [bank]
  (map (fn [v] (Character/digit v 10)) bank))

(defn first-index-of [coll value]
  (first (keep-indexed (fn [idx item] (when (= item value) idx)) coll)))

(defn find-joltage
  ([coll] (find-joltage coll 9))
  ([coll tens]
   (if-let [ten-index (first-index-of coll tens)]
     (when-let [units (drop (inc ten-index) coll)]
       (if (empty? units)
         (find-joltage coll (dec tens))
         (parse-long (str tens (apply max units)))))
     (find-joltage coll (dec tens)))))

(defn solve1 [banks]
  (let [banks (map bank->coll (s/split-lines banks))]
    (reduce + 0 (map find-joltage banks))))

(comment
  ((requiring-resolve 'hashp.install/install!))
  (solve1 sample-data)
  (solve1 data)
  (find-joltage "987654321111111")
  (find-joltage "811111111111119")
  (find-joltage "234234234234278")
  (find-joltage "818181911112111"))

