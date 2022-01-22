(ns aoc2015.03
  (:require [advent.core :refer [count-if]]
            [clojure.java.io :as io]))

(def data (slurp (io/resource "aoc2015/03.txt")))

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

(defn solve
  ([directions] (solve [0 0] {[0 0] 1} directions))
  ([location log [h & t]]
   (if h
     (let [new-location (move location h)]
       (recur new-location (update-log log new-location) t))
     log)))

(comment
  ;; First
  (count (solve data)))

(comment
  ;; Second
  (count (merge (solve (take-nth 2 data))
                (solve (take-nth 2 (drop 1 data))))))