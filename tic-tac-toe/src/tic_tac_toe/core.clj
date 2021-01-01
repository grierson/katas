(ns tic-tac-toe.core)

(defn three-in-a-row? [row]
  (when (and (apply = row)
             (not (.contains row \_)))
    (first row)))

(defn get-rows [[[tl tm tr]
                 [ml mm mr]
                 [bl bm br] :as rows]]
  (let [diagonals [[tl mm br] [tr mm bl]]
        columns [[tl ml bl]
                 [tm mm bm]
                 [tr mr br]]]
    (concat rows diagonals columns)))

(defn score [game]
  (some three-in-a-row? (get-rows game)))