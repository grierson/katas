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

(def mas-directions {:north [[-1 0] [-2 0] [-3 0]]
                     :north-east [[-1 1] [-2 2] [-3 3]]
                     :east [[0 1] [0 2] [0 3]]
                     :south-east [[1 1] [2 2] [3 3]]
                     :south [[1 0] [2 0] [3 0]]
                     :south-west [[1 -1] [2 -2] [3 -3]]
                     :west [[0 -1] [0 -2] [0 -3]]
                     :north-west [[-1 -1] [-2 -2] [-3 -3]]})

(def xmas-directions {:north-east [-1 1]
                      :south-east [1 1]
                      :south-west [1 -1]
                      :north-west [-1 -1]})

;; S  S  S
;;  A A A
;;   MMM 
;; SAMXMAS
;;   MMM
;;  A A A
;; S  S  S

(defn path [directions direction [ly lx]]
  (let [path #p (get directions direction)]
    (map (fn [[y x]] [(+ ly y) (+ lx x)]) path)))

(defn xmas-path [directions direction [ly lx]]
  (let [[dy dx] (get directions direction)]
    [(+ ly dy) (+ lx dx)]))

(defn mas-surrounding [location]
  {:north (path mas-directions :north location)
   :north-east (path mas-directions  :north-east location)
   :east (path mas-directions  :east location)
   :south-east (path mas-directions  :south-east location)
   :south (path mas-directions  :south location)
   :south-west (path mas-directions  :south-west location)
   :west (path mas-directions  :west location)
   :north-west (path mas-directions  :north-west location)})

(defn xmas-surrounding [location]
  {:north-east (xmas-path xmas-directions :north-east location)
   :south-east (xmas-path xmas-directions :south-east location)
   :south-west (xmas-path xmas-directions :south-west location)
   :north-west (xmas-path xmas-directions :north-west location)})

(defn find-all [target grid]
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

(defn walk [grid path]
  (map (fn [location] (get-in grid location)) path))

(defn mas? [result]
  (= result [\M \A \S]))

(defn count-surrounding-mas [grid location]
  (let [ds (keys mas-directions)
        result (map (fn [d] [d (walk grid (path mas-directions d location))]) ds)]
    (count (filter (comp mas? second) result))))

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

(defn xmas-locations [grid locations]
  (map (fn [location] [location (x-mas? grid location)]) locations))

(defn solve [raw]
  (let [data (parse raw)
        x-locations (find-all \X data)
        results (map (partial count-surrounding-mas data) x-locations)]
    (reduce + 0 results)))

(defn solve2 [raw]
  (let [data (parse raw)
        a-locations (find-all \A data)]
    (count (filter (fn [[_ xmas]] xmas) (xmas-locations data a-locations)))))

(comment
  (solve sample)
  (solve input)
  (solve2 sample)
  (solve2 input))
