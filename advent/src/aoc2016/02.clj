(ns aoc2016.02
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn move [position direction]
  (condp = direction
    \L (if (#{1 4 7} position) position (- position 1))
    \R (if (#{3 6 9} position) position (+ position 1))
    \U (if (#{1 2 3} position) position (- position 3))
    \D (if (#{7 8 9} position) position (+ position 3))))

(defn get-number [position [h & t]]
  (if h
    (recur (move position h) t)
    position))

(defn solve [lines]
  (reduce
    (fn [state instructions] (conj state (get-number (last state) instructions)))
    [(get-number 5 (first lines))]
    (rest lines)))

(comment
  (def lines (str/split-lines (slurp (io/resource "aoc2016/02.txt"))))
  (solve lines))
