(ns card-game-war.game)

;; Domain

(def suits [:spade :club :diamond :heart])
(def ranks [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace])

;; Logic

(defn value [card]
  "Get card value"
  (+
   (.indexOf ranks (card :rank))
   (.indexOf suits (card :suit))))


(defn play-round [player1-card player2-card]
  (if (> (value player1-card) (value player2-card))
    player1-card
    player2-card))


(defn play-game [player1-cards player2-cards]
  (cond
    (empty? player1-cards) "Player 2 Wins"
    (empty? player2-cards) "Player 1 Wins"
    :else (play-round (first player1-cards)
                      (first player2-cards))))
