(ns tic-tac-toe.core)

(defn check-row [game]
  (some (fn [[l :as row]] (if (and (apply = row)
                                   (not= l \_)) l)) game))

(defn check-column [column]
  (when (and (apply = column)
             (not= (first column) \_))
    (first column)))

(defn check-columns [game]
  (let [left-column (map (fn [row] (first row)) game)
        middle-column (map (fn [row] (second row)) game)
        right-column (map (fn [row] (last row)) game)]
    (some #(if (some? %) %) [(check-column left-column)
                             (check-column middle-column)
                             (check-column right-column)])))

(defn check [game]
  (let [rows (check-row game)
        columns (check-columns game)]
    (or rows columns)))

(defn score [game]
  (if (nil? game)
    nil
    (check game)))