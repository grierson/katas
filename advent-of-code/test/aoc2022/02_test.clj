(ns aoc2022.02-test
  (:require [clojure.test :refer :all]
            [aoc2022.02 :refer [parse
                                sample
                                score-round
                                score
                                score2
                                parse2]]))

;; Opponent | Me
;; A/X = Rock = 1
;; B/Y = Paper = 2
;; C/Z = Scissors = 3

;; 0 = lose
;; 3 = Draw
;; 6 = Win

(deftest game-test
  (is (= [[:ROCK :PAPER]
          [:PAPER :ROCK]
          [:SCISSORS :SCISSORS]]
         (parse sample))))

(deftest score-round-test
  (is (= 8 (score-round [:ROCK :PAPER])))
  (is (= 1 (score-round [:PAPER :ROCK])))
  (is (= 6 (score-round [:SCISSORS :SCISSORS]))))

(deftest score-test
  (is (= 15 (score (parse sample)))))

(deftest score2-test2
  (is (= 12 (score2 (parse2 sample)))))
