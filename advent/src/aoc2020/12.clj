(ns aoc2020.12
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(def sample "F10\nN3\nF7\nR90\nF11")

; state [:E [0 0]]
; instruction ["N" 10]

(defn parse-instruction [instruction]
  (let [[_ direction amount] (re-find #"(\w)(\d+)" instruction)]
    [direction (Integer/parseInt amount)]))

(defn forward [[facing [x y]] amount]
  (condp = facing
    :N [facing [x (+ y amount)]]
    :E [facing [(+ x amount) y]]
    :S [facing [x (- y amount)]]
    :W [facing [(- x amount) y]]))

(defn north [[facing [x y]] amount]
  [facing [x (+ y amount)]])

(defn east [[facing [x y]] amount]
  [facing [(+ x amount) y]])

(defn south [[facing [x y]] amount]
  [facing [x (- y amount)]])

(defn west [[facing [x y]] amount]
  [facing [(- x amount) y]])

(def clockwise {:N :E
                :E :S
                :S :W
                :W :N})

(def counter-clockwise {:N :W
                        :E :N
                        :S :E
                        :W :S})

(defn left [[facing position] amount]
  (let [rotate ({90 2
                 180 3
                 270 4} amount)
        facing* (last (take rotate (iterate counter-clockwise facing)))]
    [facing* position]))


(defn right [[facing position] amount]
  (let [rotate ({90 2
                 180 3
                 270 4} amount)
        facing* (last (take rotate (iterate clockwise facing)))]
    [facing* position]))

(defn move [state [direction amount]]
  (condp = direction
    "N" (north state amount)
    "E" (east state amount)
    "S" (south state amount)
    "W" (west state amount)
    "F" (forward state amount)
    "R" (right state amount)
    "L" (left state amount)))

(def input (str/split-lines (slurp (io/resource "aoc2020/12.txt"))))

(comment
  (let [[_ [x y]] (reduce move [:E [0 0]] (map parse-instruction input))]
    (+ x y)))
