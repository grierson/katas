(ns aoc2020.11
  (:require [clojure.string :as str]))


(def sample "#.##.##.##\n#######.##\n#.#.#..#..\n####.##.##\n#.##.##.##\n#.#####.##\n..#.#.....\n##########\n#.######.#\n#.#####.##")
(def expected "#.LL.L#.##\n#LLLLLL.L#\nL.L.L..L..\n#LLL.LL.L#\n#.LL.LL.LL\n#.LLLL#.##\n..L.L.....\n#LLLLLLLL#\n#.LLLLLL.L\n#.#LLLL.##")

(def lines (str/split-lines sample))

; first line - first character
; [_ _] [1 0]
; [0 1]  [1 1]

; first line - second character
; [0 0] [_ _] [2 0]
; [0 1]  [1 1] [2 1]

(defn neighbour-locations
  "int int [int int] -> [[int int]]
   ===
   Returns all valid surrounding spaces"
  [max-x max-y [x y]]
  (for [*x (range (max 0 (dec x)) (inc (min max-x (inc x))))
        *y (range (max 0 (dec y)) (inc (min max-y (inc y))))
        :when (not (and (= x *x) (= y *y)))]
    [*y *x]))

(defn neighbour->value
  "[string] [int int] -> char
   ===
   Returns value at position within lines"
  [lines neighbour]
  (get-in lines neighbour))

(defn neighbours->values
  "[string] [[int int]] -> [char]
   ===
   Get all values of surrounding space"
  [lines neighbours]
  (map #(neighbour->value lines %) neighbours))

(defn crowded?
  "4 or more occupied (#) seats"
  [neighbours]
  (let [occupied (count (filter #{\#} neighbours))]
    (>= occupied 4)))

(defn open?
  "No occupied (#) seats"
  [neighbours]
  (not-any? #{\#} neighbours))

(defn get-neighbours
  "[string] int int [int int] -> [char]
   ===
   Given lines, boundaries (max-x max-y), position ([x y])
   Then return all values surrounding position"
  [lines max-x max-y position]
  (neighbours->values lines (neighbour-locations max-x max-y position)))

(defn apply-rules
  "[char] char -> char
   ===
   Returns new value for position based on neighbours"
  [neighbours value]
  (condp = value
    \# (if (crowded? neighbours) \L value)
    \L (if (open? neighbours) \# value)
    :else value))

(comment
  (let [location [0 1]]
    (get-neighbours lines 10 10 location) (get-in lines location))
  (let [location [0 1]]
    (apply-rules (get-neighbours lines 10 10 location) (get-in lines location))))

(comment
  (let [max-x (dec (count (first lines)))
        max-y (dec (count lines))]))

