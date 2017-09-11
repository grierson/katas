(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all]))


;; fill in  tests for your game
(deftest test-play-round
  (testing "the highest rank wins the cards in the round"
    (let [high {:rank :ace :suit :spade}
          low {:rank 2 :suit :spade}]
      (is (= (play-round high low) high))))


  (testing "queens are higher rank than jacks"
    (let [queen {:rank :queen :suit :spade}
          jack {:rank :jack :suit :spade}]
      (is (= (play-round queen jack) queen))))


  (testing "kings are higher rank than queens"
    (let [king {:rank :king :suit :spade}
          queen {:rank :queen :suit :spade}]
      (is (= (play-round king queen) king))))


  (testing "aces are higher rank than kings"
    (let [ace {:rank :ace :suit :spade}
          king {:rank :king :suit :spade}]
      (is (= (play-round ace king) ace))))


  (testing "if the ranks are equal, clubs beat spades"
    (let [club {:rank :ace :suit :club}
          spade {:rank :ace :suit :spade}]
      (is (= (play-round club spade) club))))


  (testing "if the ranks are equal, diamonds beat clubs"
    (let [club {:rank :ace :suit :club}
          diamond {:rank :ace :suit :diamond}]
      (is (= (play-round club diamond) diamond))))
  (testing "if the ranks are equal, hearts beat diamonds"))

(deftest test-play-game
  (testing "the player loses when they run out of cards"))

