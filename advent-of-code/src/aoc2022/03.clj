(ns aoc2022.03
  (:require
   [clojure.java.io :as io]
   [clojure.set :as set]
   [clojure.string :as str]))

(def sample
  "vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw")

(def data (slurp (io/resource "aoc2022/03.txt")))

(defn find-duplicate [sections]
  (first (apply set/intersection (map set sections))))

(defn split-rucksack [rucksack]
  (let [l (count rucksack)
        half (/ l 2)]
    [(subs rucksack 0 half) (subs rucksack half)]))

(def alphabet "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")
(def priority (into {} (map vector alphabet (range 1 (inc (count alphabet))))))

(defn solve-fn [split-fn rucksacks]
  (->> rucksacks
       str/split-lines
       split-fn
       (map find-duplicate)
       (map priority)
       (reduce +)))

(def solve (partial solve-fn (fn [coll] (map split-rucksack coll))))
(def solve2 (partial solve-fn (fn [coll] (partition 3 coll))))

(comment
  (solve sample)
  (solve data)
  (solve2 sample)
  (solve2 data))
