(ns aoc2025.04
  (:require
   [aoc2023.03 :as aoc2023]
   [clojure.java.io :as io]
   [clojure.string :as s]))

(def sample-data
  "..@@.@@@@.
@@@.@.@.@@
@@@@@.@.@@
@.@@@@..@.
@@.@@@@.@@
.@@@@@@@.@
.@.@.@.@@@
@.@@@.@@@@
.@@@@@@@@.
@.@.@@@.@.")

(def data (slurp (io/resource "aoc2025/04.txt")))

(defn get-surroundings [grid location]
  (let [surroundings (aoc2023/surround-locations location)]
    (remove nil? (map (fn [surrounding] (get-in grid surrounding)) surroundings))))

(defn roll? [grid location]
  (let [item (get-in grid location)]
    (= item \@)))

(defn valid-location [grid location]
  (when (roll? grid location)
    (let [surroundings (get-surroundings grid location)
          rolls (filter (fn [item] (= \@ item)) surroundings)]
      (when (< (count rolls) 4) location))))

(defn solve1 [data]
  (let [grid (s/split-lines data)]
    (->> grid
         (map-indexed
          (fn [x line]
            (map-indexed
             (fn [y _item] (valid-location grid [x y]))
             line)))
         (apply concat)
         (remove nil?)
         count)))

(comment
  (solve1 sample-data)
  (solve1 data))
