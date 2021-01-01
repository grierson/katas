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

(defn check-forward-slash [[[tl]
                            [_ m]
                            [_ _ br]]]
  (let [diagonal [tl m br]]
    (when (and (apply = diagonal)
               (not (.contains diagonal \_)))
      tl)))

(defn check-back-slash [[[_ _ tr]
                         [_ m]
                         [bl]]]
  (let [diagonal [tr m bl]]
    (when (and (apply = diagonal)
               (not (.contains diagonal \_)))
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