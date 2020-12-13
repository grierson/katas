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

(defn get-neighbours [[x y] max-x max-y]
  (for [*x (range (max 0 (dec x)) (inc (min max-x (inc x))))
        *y (range (max 0 (dec y)) (inc (min max-y (inc y))))
        :when (not (and (= x *x) (= y *y)))]
    [*x *y]))

(defn neighbour->value [lines neighbour]
  (get-in lines neighbour))

(defn neighbours->values [lines neighbours]
  (map #(neighbour->value lines %) neighbours))

(defn crowded? [neighbours]
  (let [occupied (count (filter #{\#} neighbours))]
    (>= occupied 4)))

(defn open? [neighbours]
  (not-any? #{\#} neighbours))

(crowded? (neighbours->values lines (get-neighbours [0 0] 10 10)))
(open? (neighbours->values lines (get-neighbours [0 0] 10 10)))

(comment
  (let [max-x (dec (count (first lines)))
        max-y (dec (count lines))]))

