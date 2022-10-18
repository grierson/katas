(ns aoc2015.03
  (:require [advent.core :refer [count-if]]
            [clojure.java.io :as io]))

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

(defn solve
  ([directions] (solve [0 0] {[0 0] 1} directions))
  ([location log directions]
   (reduce
    (fn [{:keys [location log]} direction]
      (let [new-location (move location direction)]
        (recur {:location new-location
                :log (update-log log new-location)}
               direction)))
    {:location location
     :log log}
    directions)))

(comment
  (def data (slurp (io/resource "aoc2015/03.txt")))

  ;; First
  (count (solve data))
  (= 2572 (count (solve data)))

  ;; Second
  (count (merge (solve (take-nth 2 data))
                (solve (take-nth 2 (drop 1 data))))))
