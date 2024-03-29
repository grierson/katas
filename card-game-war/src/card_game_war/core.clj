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

(defn get-value [{:keys [value]}]
  (values value))

(defn mc [v]
  (->Card :hearts v))

(defn play-round [{:keys [player1 player2]}]
  (let [[c1 & c1s] player1
        [c2 & c2s] player2
        c1v (get-value c1)
        c2v (get-value c2)]
    (cond
      (> c1v c2v) {:player1 (conj (vec c1s) c1 c2)
                   :player2 (vec c2s)}
      (> c2v c1v) {:player1 (vec c1s)
                   :player2 (conj (vec c2s) c2 c1)})))

(defn play-game [{:keys [player1 player2] :as game}]
  (cond
    (empty? player1) "player 2 wins"
    (empty? player2) "player 1 wins"
    :else (recur (play-round game))))
