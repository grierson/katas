(ns tennis.core
  (:require [clojure.core.match :refer [match]]))

(defn add [score]
  (case score
    0 15
    15 30
    30 40
    40 :deuce))

(defn score [player1 player2 winner]
  (let [player1-wins "Player 1 wins"
        player2-wins "Player 2 wins"]
    (match [player1 player2 winner]
           [40 :deuce 1] [40 40]
           [:deuce 40 2] [40 40]
           [:deuce 40 1] player1-wins
           [40 :deuce 2] player2-wins
           [40 (_ :guard #(not= 40 %)) 1] player1-wins
           [(_ :guard #(not= 40 %)) 40 2] player2-wins
           [_ _ 1] [(add player1) player2]
           [_ _ 2] [player1 (add player2)])))
