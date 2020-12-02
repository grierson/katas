(ns aoc2015.03
  (:require [advent.core :refer [read-file count-if]]))

(def data (first (read-file "aoc2015/03.txt")))

(comment
  ([[x y] cnt]))

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

(def result (solve data))

(get result [0 0])

(count-if (fn [[_ v]] (>= v 2)) result)
