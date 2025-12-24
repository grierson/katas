(ns aoc2025.03
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]
   [clojure.core :as c]))

(def sample-data "987654321111111
811111111111119
234234234234278
818181911112111")

(def data (slurp (io/resource "aoc2025/03.txt")))

(defn bank->coll
  "bank string -> [number]"
  [bank]
  (map (fn [v] (Character/digit v 10)) bank))

(defn find-indexes
  "[index of number]"
  [coll number]
  (keep-indexed (fn [idx v] (when (= v number) idx)) coll))

(defn find-joltage
  ([coll] (find-joltage coll 9))
  ([coll tens]
   (let [tens-indexes (find-indexes coll tens)]
     (if (empty? tens-indexes)
       (find-joltage coll (dec tens))
       (when-let [units (drop (inc (first tens-indexes)) coll)]
         (if (empty? units)
           (find-joltage coll (dec tens))
           (parse-long (str tens (apply max units)))))))))

(comment
  ((requiring-resolve 'hashp.install/install!))
  (find-joltage (bank->coll "987654321111111"))
  (find-joltage (bank->coll "811111111111119"))
  (find-joltage (bank->coll "234234234234278"))
  (find-joltage (bank->coll "818181911112111")))

(defn solve1 [banks]
  (let [banks (map bank->coll (str/split-lines banks))]
    (reduce + 0 (map find-joltage banks))))

(comment
  ((requiring-resolve 'hashp.install/install!))
  (solve1 sample-data)
  (solve1 data)
  (find-joltage "987654321111111")
  (find-joltage "811111111111119")
  (find-joltage "234234234234278")
  (find-joltage "818181911112111"))

