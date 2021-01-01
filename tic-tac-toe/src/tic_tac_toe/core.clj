(ns tic-tac-toe.core)

(defn three-in-a-row? [row]
  (when (and (apply = row)
             (not (.contains row \_)))
    (first row)))

(defn get-columns [game]
  (let [left-column (map first game)
        middle-column (map second game)
        right-column (map last game)]
    [left-column middle-column right-column]))

(defn get-diagonals [[[tl _ tr]
                      [_ m _]
                      [bl _ br]]]
  [[tl m br] [tr m bl]])

(defn score [game]
  (when (some? game)
    (let [columns (get-columns game)
          diagonals (get-diagonals game)]
      (some three-in-a-row? (concat game columns diagonals)))))