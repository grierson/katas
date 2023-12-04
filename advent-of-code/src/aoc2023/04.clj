(ns aoc2023.04
  (:require
   [clojure.java.io :as io]
   [clojure.set :as set]
   [clojure.string :as str]))

(def data (slurp (io/resource "aoc2023/04.txt")))

(def sample-data
  "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11")

(defn parse
  [line]
  (let [id (parse-long (re-find #"\d+" line))
        numbers (-> line
                    (str/split #"\:")
                    second
                    (str/split #"\|"))
        numbers (map
                 (fn [numbers]
                   (->> (re-seq #"\d+" numbers)
                        (map parse-long)
                        set))
                 numbers)]
    {id numbers}))

(def scoring {1 1
              2 2
              3 4
              4 8
              5 16
              6 32
              7 64
              8 128
              9 256
              10 512})

(defn score [numbers]
  (get scoring (count (apply set/intersection numbers)) 0))

(defn solve [data]
  (let [lines (str/split-lines data)
        cards (reduce
               (fn [state line] (merge state (parse line)))
               {}
               lines)
        scores (map (comp score second) cards)]
    (reduce + 0 scores)))

(comment
  (solve sample-data)
  (solve data))
