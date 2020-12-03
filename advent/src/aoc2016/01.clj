(ns aoc2016.01
  (:require [advent.core :refer [read-file parse-int]]
            [clojure.string :as str]))

(def data (first (read-file "aoc2016/01.txt")))

(defn parse-direction [direction]
  (let [[_ turn steps] (re-matches #"(.)(\d+)" direction)]
    [(first turn) (parse-int steps)]))

(def guide (map (comp parse-direction str/trim) (str/split data #",")))

(def directions {\N {\L \W
                     \R \E}
                 \E {\L \N
                     \R \S}
                 \S {\L \E
                     \R \W}
                 \W {\L \S
                     \R \N}})

(defn update-coords [[x y] direction steps]
  (condp = direction
    \N [x (- y steps)]
    \E [(+ x steps) y]
    \S [x (+ y steps)]
    \W [(- x steps) y]))

(defn foo [[facing coords] [turning steps]]
  (let [new-facing (get-in directions [facing turning])]
    [new-facing (update-coords coords new-facing steps)]))

(loop [state [\N [0 0]]
       [h & t] guide]
  (if h
    (recur (foo state h) t)
    state))

(+ -140 -138)


