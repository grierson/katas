(ns tic-tac-toe.core)

(defn check [game]
  (let [rows (some (fn [[l :as row]] (if (and (apply = row)
                                              (not= l \_)) l)) game)
        columns (let [column (map (fn [row] (first row)) game)]
                  (when (and (apply = column)
                             (not= (first column) \_))
                    (first column)))]
    (or rows columns)))

(defn score [game]
  (if (nil? game)
    nil
    (check game)))