(ns tic-tac-toe.core)

(defn score [game]
  (if (nil? game)
    nil
    (ffirst game)))