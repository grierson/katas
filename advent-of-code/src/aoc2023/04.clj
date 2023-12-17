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

(defn- make-cards [lines]
  (reduce
   (fn [state line] (merge state (parse line)))
   {}
   lines))

(defn solve [data]
  (let [lines (str/split-lines data)
        cards (make-cards lines)
        scores (map (comp score second) cards)]
    (reduce + 0 scores)))

(require '[hashp.core])

(defn score2 [[id numbers]]
  (let [matches (count (apply set/intersection numbers))]
    (range (inc id) (+ (inc id) matches))))

(defn make-results [cards]
  (reduce
   (fn [state [id _ :as card]]
     (let [matches (score2 card)]
       (assoc
        state
        id
        matches)))
   {}
   cards))

(comment
  (make-results (make-cards (str/split-lines sample-data))))

(defn update-vals [map vals f]
  (reduce #(update-in % [%2] f) map vals))

(defn process
  [results]
  (let [state (reduce (fn [state id] (assoc state id 1)) {} (keys results))]
    (reduce
     (fn [state [id matches]]
       (let [instances (get state id)]
         (update-vals state matches #(+ % instances))))
     state
     results)))

(comment
  (process {1 [2 3 4 5]
            2 [3 4]
            3 [4 5]
            4 [5]
            5 []
            6 []}))

(defn solve2 [data]
  (let [lines (str/split-lines data)
        cards (make-cards lines)
        results (make-results cards)
        processed (process results)]
    (reduce + 0 (vals processed))))

(comment
  (solve sample-data)
  (solve2 sample-data)
  (solve data)
  (solve2 data))
