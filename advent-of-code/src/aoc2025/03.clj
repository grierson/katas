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

(comment
  (find-indexes (bank->coll "987654321111111") 1))

(defn indexed-bank
  "{number [indexes]}"
  [coll]
  (into {} (map (fn [step] {step (find-indexes coll step)}) (range 1 10))))

(defn find-units
  "Find unit that comes after tens index"
  [indexed-bank tens-index]
  (let [possible-units (filter (fn [[_ v]] (some (fn [idx] (> idx tens-index)) v)) indexed-bank)]
    (if (empty? possible-units)
      nil
      (apply max (keys (into {} possible-units))))))

(defn find-joltage
  ([indexed-bank] (find-joltage indexed-bank 9))
  ([indexed-bank tens]
   (let [tens-indexes (get indexed-bank tens)]
     (if (empty? tens-indexes)
       (find-joltage indexed-bank (dec tens))
       (if-let [unit (some (fn [tens-index] (when-let [unit (find-units indexed-bank tens-index)] unit)) tens-indexes)]
         (parse-long (str tens unit))
         (find-joltage indexed-bank (dec tens)))))))

(comment
  ((requiring-resolve 'hashp.install/install!))
  (find-joltage (indexed-bank (bank->coll "987654321111111")))
  (find-joltage (indexed-bank (bank->coll "811111111111119")))
  (find-joltage (indexed-bank (bank->coll "234234234234278")))
  (find-joltage (indexed-bank (bank->coll "818181911112111"))))

(defn solve1 [banks]
  (let [banks (map bank->coll (str/split-lines banks))]
    (reduce + 0
            (map (fn [bank] (find-joltage (indexed-bank bank))) banks))))

(comment
  ((requiring-resolve 'hashp.install/install!))
  (solve1 sample-data)
  (solve1 data)
  (find-joltage "987654321111111")
  (find-joltage "811111111111119")
  (find-joltage "234234234234278")
  (find-joltage "818181911112111"))

