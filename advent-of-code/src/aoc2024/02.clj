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

(defn numbers [s] (mapv parse-long (re-seq #"\d+" s)))

(defn parse [data] (map numbers (str/split-lines data)))

(defn ordered? [report]
  (or (apply < report)
      (apply > report)))

(defn distance [[left right]] (abs (- left right)))

(defn small?
  [report]
  (let [distances (map distance (partition 2 1 report))]
    (every? (fn [x] (<= 1 x 3)) distances)))

(defn permutations [report]
  (map-indexed
   (fn [idx _]
     (concat (subvec report 0 idx)
             (subvec report (inc idx))))
   report))

(defn valid-report? [report]
  (and (ordered? report) (small? report)))

(defn valid-dampen-report? [report]
  (or (valid-report? report)
      (some valid-report? (permutations report))))

(defn solve1 [data]
  (count (filter true? (map valid-report? (parse data)))))

(defn solve2 [data]
  (count (filter true? (map valid-dampen-report? (parse data)))))

(comment
  (solve1 sample)
  (solve1 data)
  (solve2 sample)
  (solve2 data))
