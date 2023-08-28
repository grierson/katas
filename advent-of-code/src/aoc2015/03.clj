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

(def default-state
  {:location [0 0]
   :log {[0 0] 1}})

(defn- update-state
  [{:keys [location log]} direction]
  (let [new-location (move location direction)]
    {:location new-location
     :log (update-log log new-location)}))

(defn track
  [directions]
  (reduce update-state default-state directions))

(defn solve1 [directions]
  (count (:log (track directions))))

(defn solve2 [directions]
  (count (merge (:log (track (take-nth 2 directions)))
                (:log (track (take-nth 2 (drop 1 directions)))))))

(comment
  (def directions (slurp (io/resource "aoc2015/03.txt")))

  ;; First
  (solve1 directions)

  ;; Second
  (solve2 directions))
