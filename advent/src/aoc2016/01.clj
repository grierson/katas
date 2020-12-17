(ns aoc2016.01
  (:require [advent.core :refer [read-file parse-int]]
            [clojure.string :as str]))

(def data (first (read-file "aoc2016/01.txt")))

(defn parse-direction [direction]
  (let [[_ turn steps] (re-matches #"(.)(\d+)" direction)]
    [(first turn) (parse-int steps)]))

(def guide (map (comp parse-direction str/trim) (str/split data #",")))

(def compass {\N {\L \W
                  \R \E}
              \E {\L \N
                  \R \S}
              \S {\L \E
                  \R \W}
              \W {\L \S
                  \R \N}})

(defn update-coords [[x y] facing steps]
  (condp = facing
    \N [x (- y steps)]
    \E [(+ x steps) y]
    \S [x (+ y steps)]
    \W [(- x steps) y]))

(defn update-state [[facing coords] [turning steps]]
  (let [new-facing (get-in compass [facing turning])]
    [new-facing (update-coords coords new-facing steps)]))

(defn solve [[_ coords :as state] [h & t]]
  (if h
    (recur (update-state state h) t)
    (apply + (map #(Math/abs %) coords))))

(defn solve2 [visited [_ coords :as state] [h & t]]
  (if (contains? visited coords)
    coords
    (when h
      (recur (conj visited coords) (update-state state h) t))))

(comment
  ;first
  (solve [\N [0 0]] guide)
  (solve2 #{} [\N [0 0]] guide))


