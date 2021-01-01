(ns tic-tac-toe.core)

(defn score [game]
  (if (nil? game)
    nil
    (some (fn [[l m r]] (if (= l m r) l)) game)))