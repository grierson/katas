(ns aoc2024.02
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(def data (slurp (io/resource "aoc2024/02.txt")))

(def sample
  "7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9")

(defn numbers [s] (map parse-long (re-seq #"\d+" s)))

(defn parse [data] (map numbers (str/split-lines data)))

(defn ordered?
  "All aces or desc"
  [report]
  (let [distances (partition 2 1 report)]
    (or (every? (fn [[left right]] (< left right)) distances)
        (every? (fn [[left right]] (> left right)) distances))))

(defn distance [[left right]] (abs (- left right)))
(def aany? (complement not-any?))

(defn any-big-step?
  "distance between 1 - 3"
  [report]
  (let [distances (map distance (partition 2 1 report))]
    (or (aany? (fn [x] (< x 0)) distances)
        (aany? (fn [x] (> x 3)) distances))))

(defn valid-report? [report]
  (and (ordered? report)
       (not (any-big-step? report))))

(defn solve1 [data]
  (count (filter true? (map valid-report? (parse data)))))

(comment
  (solve1 sample)
  (solve1 data))
