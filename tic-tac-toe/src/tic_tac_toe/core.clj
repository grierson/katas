(ns tic-tac-toe.core)

(defn score [game]
  (if (some? game)
    (ffirst game)
    nil))