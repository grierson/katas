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

;; S  S  S
;;  A A A
;;   MMM 
;; SAMXMAS
;;   MMM
;;  A A A
;; S  S  S

(def mas-locations {:north [[-1 0] [-2 0] [-3 0]]
                    :north-east [[-1 1] [-2 2] [-3 3]]
                    :east [[0 1] [0 2] [0 3]]
                    :south-east [[1 1] [2 2] [3 3]]
                    :south [[1 0] [2 0] [3 0]]
                    :south-west [[1 -1] [2 -2] [3 -3]]
                    :west [[0 -1] [0 -2] [0 -3]]
                    :north-west [[-1 -1] [-2 -2] [-3 -3]]})

;; M M 
;;  A
;; S S
(def xmas-locations {:north-east [-1 1]
                     :south-east [1 1]
                     :south-west [1 -1]
                     :north-west [-1 -1]})

(defn path [route [ly lx]]
  (map (fn [[y x]] [(+ ly y) (+ lx x)]) route))

(defn xmas-path [[ry rx] [ly lx]]
  [(+ ly ry) (+ lx rx)])

(defn mas-surrounding [location]
  {:north (path (:north mas-locations) location)
   :north-east (path (:north-east mas-locations) location)
   :east (path (:east mas-locations) location)
   :south-east (path (:south-east mas-locations) location)
   :south (path (:south mas-locations) location)
   :south-west (path (:south-west mas-locations) location)
   :west (path (:west mas-locations) location)
   :north-west (path (:north-west mas-locations) location)})

(defn xmas-surrounding [location]
  {:north-east (xmas-path (:north-east xmas-locations) location)
   :south-east (xmas-path (:south-east xmas-locations) location)
   :south-west (xmas-path (:south-west xmas-locations) location)
   :north-west (xmas-path (:north-west xmas-locations) location)})

(defn find-all
  "Find every location of target in grid"
  [target grid]
  (reduce
   (fn [state [row-idx row]]
     (let [matches (keep-indexed
                    (fn [col-idx letter]
                      (when (= letter target) [row-idx col-idx]))
                    row)]
       (concat state matches)))
   []
   (map-indexed vector grid)))

(defn parse [data] (str/split-lines data))

(defn follow
  "Fetch each letter on path"
  [grid path]
  (map (fn [location] (get-in grid location)) path))

(defn mas? [result]
  (= result [\M \A \S]))

(defn count-surrounding-mas
  "Find each path from X"
  [grid location]
  (let [directions (keys mas-locations)
        result
        (map (fn [direction]
               [location
                direction
                (follow grid
                        (path (get mas-locations direction) location))])
             directions)]
    (count (filter (fn [[_ _ letters]] (mas? letters)) result))))

(defn x-mas? [grid location]
  (let [{:keys [north-east south-east south-west north-west]} (xmas-surrounding location)
        ne (get-in grid north-east)
        se (get-in grid south-east)
        sw (get-in grid south-west)
        nw (get-in grid north-west)]
    (and
     (or (and (= nw \M)
              (= se \S))
         (and (= nw \S)
              (= se \M)))
     (or (and (= ne \M)
              (= sw \S))
         (and (= ne \S)
              (= sw \M))))))

(defn xmas-location? [grid location] [location (x-mas? grid location)])

(defn solve [raw]
  (let [data (parse raw)
        x-locations (find-all \X data)
        results (map (partial count-surrounding-mas data) x-locations)]
    (reduce + 0 results)))

(defn solve2 [raw]
  (let [data (parse raw)
        a-locations (find-all \A data)]
    (count (filter (fn [[_ xmas]] xmas) (map (partial xmas-location? data) a-locations)))))

(comment
  (solve sample)
  (solve input)
  (solve2 sample)
  (solve2 input))
