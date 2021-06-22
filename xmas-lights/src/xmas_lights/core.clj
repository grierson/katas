(ns xmas-lights.core)


(defn make-grid [columns rows]
  (->> (repeat columns false)
       (into [])
       (repeat rows)
       (into [])))

(defn turn-on [grid coords]
  (assoc-in grid coords true))

(defn turn-off [grid coords]
  (assoc-in grid coords false))

(defn toggle [grid coords]
  (update-in grid coords not))

(defn get-range [[p1x p1y] [p2x p2y]]
  (for [x (range p1x (inc p2x))
        y (range p1y (inc p2y))]
    [x y]))

(defn many [f grid p1 p2]
  (let [coll (get-range p1 p2)]
    (reduce f grid coll)))