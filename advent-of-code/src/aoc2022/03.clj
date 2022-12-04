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

(defn duplicates [rucksack]
  (let [l (count rucksack)
        half (/ l 2)
        sections [(subs rucksack 0 half) (subs rucksack half)]]
    (find-duplicate sections)))

(def duplicates2 find-duplicate)

(def alphabet "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")
(def priority (into {} (map vector alphabet (range 1 (inc (count alphabet))))))

(defn solve [rucksacks]
  (->> rucksacks
       str/split-lines
       (map duplicates)
       (map priority)
       (reduce +)))

(defn solve2 [rucksacks]
  (->> rucksacks
       str/split-lines
       (partition 3)
       (map find-duplicate)
       (map priority)
       (reduce +)))

(comment
  (solve sample)
  (solve data)
  (solve2 sample)
  (solve2 data))
