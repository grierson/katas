(ns xmas-lights.core)


(defn make-grid [columns rows]
  (->> (repeat columns false)
       (into [])
       (repeat rows)
       (into [])))

(defn turn-on [grid coords]
  (update-in grid coords not))
