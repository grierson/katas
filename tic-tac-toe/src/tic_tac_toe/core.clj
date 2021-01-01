(ns tic-tac-toe.core)

(defn check [game]
  (let [rows (some (fn [[l m r]] (if (and (= l m r)
                                          (not= l \_)) l)) game)]
    (or rows)))
(defn score [game]
  (if (nil? game)
    nil
    (check game)))