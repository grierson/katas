(ns card-game-war.core)

(def suits #{:spade :clubs :hearts :diamond})
(def values {2 2
             3 3
             4 4
             5 5
             6 6
             7 7
             8 8
             9 9
             10 10
             :J 11
             :Q 12
             :K 13
             :A 14})
(defrecord Card [suit value])

(defn winner [card1 card2]
  (if
    (> (values (:value card1)) (values (:value card2))) :player1
    :player2))

(winner (->Card :heart :A) (->Card :spades :K))
