(ns aoc2024.04
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(def sample
  "MMMSXXMASM
MSAMXMSMSA
AMXSXMAAMM
MSAMASMSMX
XMASAMXAMM
XXAMMXXAMA
SMSMSASXSS
SAXAMASAAA
MAMMMXMMMM
MXMXAXMASX")

(def input (slurp (io/resource "aoc2024/04.txt")))

(def directions {:north [[-1 0] [-2 0] [-3 0]]
                 :north-east [[-1 1] [-2 2] [-3 3]]
                 :east [[0 1] [0 2] [0 3]]
                 :south-east [[1 1] [2 2] [3 3]]
                 :south [[1 0] [2 0] [3 0]]
                 :south-west [[1 -1] [2 -2] [3 -3]]
                 :west [[0 -1] [0 -2] [0 -3]]
                 :north-west [[-1 -1] [-2 -2] [-3 -3]]})

;; S  S  S
;;  A A A
;;   MMM 
;; SAMXMAS
;;   MMM
;;  A A A
;; S  S  S

(defn path [direction [ly lx]]
  (let [path (get directions direction)]
    (map (fn [[y x]] [(+ ly y) (+ lx x)]) path)))

(defn surrounding [location]
  {:north (path :north location)
   :north-east (path :north-east location)
   :east (path :east location)
   :south-east (path :south-east location)
   :south (path :south location)
   :south-west (path :south-west location)
   :west (path :west location)
   :north-west (path :north-west location)})

(defn find-all-x [grid]
  (reduce
   (fn [state [row-idx row]]
     (let [matches (keep-indexed
                    (fn [col-idx letter]
                      (when (= letter \X) [row-idx col-idx]))
                    row)]
       (concat state matches)))
   []
   (map-indexed vector grid)))

(defn parse [data] (str/split-lines data))

(defn walk [grid path]
  (map (fn [location] (get-in grid location)) path))

(defn xmas? [result]
  (= result [\M \A \S]))

(defn count-surrounding-mas [grid location]
  (let [ds (keys directions)
        result (map (fn [d] [d (walk grid (path d location))]) ds)]
    (count (filter (comp xmas? second) result))))

(defn solve [raw]
  (let [data (parse raw)
        x-locations (find-all-x data)
        results (map (partial count-surrounding-mas data) x-locations)]
    (reduce + 0 results)))

(comment
  (solve sample)
  (solve input))
