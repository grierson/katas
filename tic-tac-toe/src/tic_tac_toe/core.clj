(ns tic-tac-toe.core)

(defn check-row [row]
  (when (and (apply = row)
             (not (.contains row \_)))
    (first row)))

(defn check-column [column]
  (when (and (apply = column)
             (not (.contains column \_)))
    (first column)))

(defn check-columns [game]
  (let [left-column (map first game)
        middle-column (map second game)
        right-column (map last game)]
    (some identity (map check-column [left-column middle-column right-column]))))

(defn check-forward-slash [game]
  (let [tl (get-in game [0 0])
        m (get-in game [1 1])
        br (get-in game [2 2])]
    (when (and (= tl m br) (not= tl \_))
      tl)))

(defn check-back-slash [game]
  (let [tr (get-in game [0 2])
        m (get-in game [1 1])
        bl (get-in game [2 0])]
    (when (and (= tr m bl) (not= tr \_))
      tr)))

(defn check-diagonals [game]
  (or (check-forward-slash game)
      (check-back-slash game)))

(defn check [game]
  (let [rows (some check-row game)
        columns (check-columns game)
        diagonals (check-diagonals game)]
    (or rows columns diagonals)))

(defn score [game]
  (if (nil? game)
    nil
    (check game)))