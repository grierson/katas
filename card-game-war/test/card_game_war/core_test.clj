(ns card-game-war.core-test
  (:require
   [clojure.test :refer [deftest testing is]]
   [card-game-war.core :refer [suits play-round ->Card play-game]]))

(defn mc [v]
  (->Card :hearts v))

(deftest test-play-round
  (testing "the highest rank wins the cards in the round"
    (let [high (mc :A)
          low (mc :K)
          game {:player1 [high]
                :player2 [low]}]
      (is (= {:player1 [high low]
              :player2 []}
             (play-round game)))))
  (testing "queens are higher rank than jacks"
    (let [high (mc :Q)
          low (mc :J)
          game {:player1 [high]
                :player2 [low]}]
      (is (= {:player1 [high low]
              :player2 []}
             (play-round game)))))
  (testing "kings are higher rank than queens"
    (let [high (mc :K)
          low (mc :Q)
          game {:player1 [high]
                :player2 [low]}]
      (is (= {:player1 [high low]
              :player2 []}
             (play-round game)))))
  (testing "aces are higher rank than kings"
    (let [high (mc :A)
          low (mc :K)
          game {:player1 [high]
                :player2 [low]}]
      (is (= {:player1 [high low]
              :player2 []}
             (play-round game)))))
  (testing "hand over of cards with remaining cards"
    (is (= {:player1 [(mc :K) (mc :Q) (mc 2)]
            :player2 [(mc 3)]}
           (play-round {:player1 [(mc :Q) (mc :K)]
                        :player2 [(mc 2) (mc 3)]})))))

(deftest test-play-game
  (testing "the player loses when they run out of cards"
    (let [game {:player1 [(mc :A)]
                :player2 []}]
      (is (= "player 1 wins" (play-game game))))
    (let [game {:player1 []
                :player2 [(mc :A)]}]
      (is (= "player 2 wins" (play-game game)))))
  (testing "Player 1 wins two rounds"
    (is (= "player 1 wins"
           (play-game {:player1 [(mc :A) (mc :A)]
                       :player2 [(mc :K) (mc :K)]})))))
