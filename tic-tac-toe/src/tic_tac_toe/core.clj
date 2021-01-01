(ns tic-tac-toe.core)

(defn score [game]
  (if (some? game)
    \X
    nil))