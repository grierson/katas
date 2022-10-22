(ns aoc2016.02
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn move [position direction]
  (case direction
    \L (if (#{1 4 7} position) position (- position 1))
    \R (if (#{3 6 9} position) position (+ position 1))
    \U (if (#{1 2 3} position) position (- position 3))
    \D (if (#{7 8 9} position) position (+ position 3))))

(defn get-number [position directions]
  (reduce move position directions))

(defn- update-state [state instructions]
  (conj state (get-number (last state) instructions)))

(defn solve
  [lines]
  (apply
   str
   (reduce
    update-state
    [(get-number 5 (first lines))]
    (rest lines))))

(def diamond-keypad
  [[nil nil 1 nil nil]
   [nil 2 3 4 nil]
   [5 6 7 8 9]
   [nil \A \B \C nil]
   [nil nil \D nil nil]])

(defn find-number [coords]
  (get-in diamond-keypad coords))

(defn get-coord [[row column] direction]
  (case direction
    \U [(- row 1) column]
    \D [(+ row 1) column]
    \L [row (- column 1)]
    \R [row (+ column 1)]))

(defn valid-move? [coord direction]
  (let [new-coord (get-coord coord direction)]
    (when (find-number new-coord)
      new-coord)))

(defn try-move [coord direction]
  (if-let [new-coord (valid-move? coord direction)]
    new-coord
    coord))

(defn apply-moves [coord moves]
  (reduce try-move coord moves))

(defn solve2
  [directions]
  (apply
   str
   (map find-number
        (reduce
         (fn [state moves]
           (conj state (apply-moves (last state) moves)))
         [(apply-moves [2 0] (first directions))]
         (rest directions)))))
(comment
  (def lines (str/split-lines (slurp (io/resource "aoc2016/02.txt"))))
  (solve lines)
  (solve2 lines))
