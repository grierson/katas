(ns aoc2015.03
  (:require [advent.core :refer [read-file count-if]]))

(def data (first (read-file "aoc2015/03.txt")))

(defn move [[x y] direction]
  (condp = direction
    \^ [x (inc y)]
    \v [x (dec y)]
    \< [(dec x) y]
    \> [(inc x) y]))

(defn update-log [log location]
  (if (contains? log location)
    (update log location inc)
    (assoc log location 1)))

(defn solve [directions]
  (loop [current [0 0]
         state {[0 0] 1}
         [h & t] directions]
    (if h
      (let [a (move current h)]
        (recur a (update-log state a) t))
      state)))

(comment
  ;; First
  (count (solve data)))

