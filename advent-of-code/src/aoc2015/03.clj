(ns aoc2015.03
  (:require [clojure.java.io :as io]))

(defn move [[x y] direction]
  (case direction
    \^ [x (inc y)]
    \v [x (dec y)]
    \< [(dec x) y]
    \> [(inc x) y]))

(defn update-log [log location]
  (if (contains? log location)
    (update log location inc)
    (assoc log location 1)))

(defn track
  [directions]
  (:log
   (reduce
    (fn [{:keys [location log]} direction]
      (let [new-location (move location direction)]
        {:location new-location
         :log (update-log log new-location)}))
    {:location [0 0]
     :log {[0 0] 1}}
    directions)))

(defn solve1 [directions]
  (count (track directions)))

(defn solve2 [directions]
  (count (merge (track (take-nth 2 directions))
                (track (take-nth 2 (drop 1 directions))))))

(comment
  (def directions (slurp (io/resource "aoc2015/03.txt")))

  ;; First
  (solve1 directions)

  ;; Second
  (solve2 directions))
