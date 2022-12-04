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

(defn duplicates [rucksack]
  (let [l (count rucksack)
        half (/ l 2)
        [c1 c2] [(subs rucksack 0 half) (subs rucksack half)]]
    (set/intersection (set c1) (set c2))))

(def alphabet "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")
(def priority (into {} (map vector alphabet (range 1 (inc (count alphabet))))))

(defn solve [data]
  (->> data
       str/split-lines
       (map duplicates)
       (map first)
       (map priority)
       (reduce +)))
