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

(defn bank->coll [bank]
  (map (fn [v] (Character/digit v 10)) bank))

(comment
  (bank->coll "987654321111111"))

(defn find-indexes
  "Find indexs of value"
  [coll number]
  (keep-indexed (fn [idx v] (when (= v number) idx)) coll))

(comment
  (find-indexes (bank->coll "987654321111111") 1))

(defn find-first [coll index value]
  (if (or (<= value 0) (< index 0))
    nil
    (let [indexes (find-indexes coll value)
          after (first (filter (fn [x] (>= x index)) indexes))]
      (if after
        [after value]
        (recur coll index (dec value))))))

(comment
  (find-first (bank->coll "987654321111111") 0 9))

(defn find-joltage [bank]
  (let [coll (bank->coll bank)]
    (loop [step 9]
      (if (< step 1)
        nil
        (if-let [[tens-index tens-value] (find-first coll 0 step)]
          (if-let [[_ units-value] (some (fn [x] (find-first coll (inc tens-index) x)) (reverse (range 1 10)))]
            (parse-long (str tens-value units-value))
            (recur (dec step)))
          (recur (dec step)))))))

(defn solve1 [banks]
  (reduce + 0 (map find-joltage (str/split-lines banks))))

(comment
  ((requiring-resolve 'hashp.install/install!))
  (solve1 sample-data)
  (solve1 data)
  (find-joltage "987654321111111")
  (find-joltage "811111111111119")
  (find-joltage "234234234234278")
  (find-joltage "818181911112111"))

