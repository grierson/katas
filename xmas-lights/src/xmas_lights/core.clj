(ns xmas-lights.core)


(defn make-grid [columns rows]
  (->> (repeat columns false)
       (into [])
       (repeat rows)
       (into [])))

(defn turn-on [coords grid]
  (assoc-in grid coords true))

(defn turn-off [coords grid]
  (assoc-in grid coords false))

(defn toggle [coords grid]
  (update-in grid coords not))

(defn get-range [[p1x p1y] [p2x p2y]]
  (for [x (range p1x (inc p2x))
        y (range p1y (inc p2y))]
    [x y]))

(defn many [f p1 p2 grid]
  (let [coll (get-range p1 p2)]
    (reduce (fn [state x] (f x state)) grid coll)))

;; Is on/off function

(defn amount-on [grid]
  (count (filter true? (flatten grid))))

(comment
  (amount-on
    (->> (make-grid 1000 1000)
         (many turn-on [887 9] [959 629])
         (many turn-on [454 398] [844 448])
         (many turn-off [539 243] [559 965])
         (many turn-off [370 819] [676 868])
         (many turn-off [145 40] [370 997])
         (many turn-off [301 3] [808 453])
         (many turn-on [351 678] [951 908])
         (many toggle [720 196] [897 994])
         (many toggle [831 394] [904 860]))))
