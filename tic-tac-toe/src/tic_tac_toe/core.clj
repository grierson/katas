(ns tic-tac-toe.core)

(defn check [game]
  (some (fn [[l m r]] (if (and (= l m r)
                               (not= l \_)) l)) game))
(defn score [game]
  (if (nil? game)
    nil
    (check game)))