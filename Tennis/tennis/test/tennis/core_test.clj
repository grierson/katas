(ns tennis.core-test
  (:require [midje.sweet :refer :all]
            [tennis.core :refer :all]))

(fact "player one scores"
  (score 0 0 1) => [15 0]
  (score 15 0 1) => [30 0]
  (score 30 0 1) => [40 0]
  (score 40 0 1) => "Player 1 wins")

(fact "player two scores"
  (score 0 0 2) => [0 15]
  (score 0 15 2) => [0 30]
  (score 0 30 2) => [0 40]
  (score 0 40 2) => "Player 2 wins")

(fact "deuce"
  (score 40 40 1) => [:deuce 40]
  (score 40 :deuce 1) => [40 40]
  (score :deuce 40 2) => [40 40]
  (score :deuce 40 1) => "Player 1 wins"
  (score 40 :deuce 2) => "Player 2 wins")
