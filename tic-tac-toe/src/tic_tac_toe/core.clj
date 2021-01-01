(ns tic-tac-toe.core)

(defn score [game]
  (if (nil? game)
    nil
    (some (fn [[l m r]] (if (and (= l m r)
                                 (not= l \_)) l)) game)))