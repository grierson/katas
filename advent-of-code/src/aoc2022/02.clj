(ns aoc2022.02
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def sample
  "A Y
B X
C Z")

(def data (slurp (io/resource "aoc2022/02.txt")))

(defn decypher [letter]
  (case letter
    \A :ROCK
    \X :ROCK
    \B :PAPER
    \Y :PAPER
    \C :SCISSORS
    \Z :SCISSORS))

(defn decypher2 [letter]
  (case letter
    \A :ROCK
    \X :LOSE
    \B :PAPER
    \Y :DRAW
    \C :SCISSORS
    \Z :WIN))

(defn parse [game]
  (map
   (fn [round]
     (let [[p1 _ p2] round]
       [(decypher p1) (decypher p2)]))
   (str/split-lines game)))

(defn parse2 [game]
  (map
   (fn [round]
     (let [[p1 _ p2] round]
       [(decypher2 p1) (decypher2 p2)]))
   (str/split-lines game)))

(def shape
  {:ROCK 1
   :PAPER 2
   :SCISSORS 3})

(def outcomes
  {:ROCK {:ROCK 3
          :SCISSORS 0
          :PAPER 6}
   :PAPER {:ROCK 0
           :PAPER 3
           :SCISSORS 6}
   :SCISSORS {:ROCK 6
              :PAPER 0
              :SCISSORS 3}})

(def outcomes2
  {:ROCK {:WIN :PAPER
          :LOSE :SCISSORS
          :DRAW :ROCK}
   :PAPER {:WIN :SCISSORS
           :LOSE :ROCK
           :DRAW :PAPER}
   :SCISSORS {:WIN :ROCK
              :LOSE :PAPER
              :DRAW :SCISSORS}})

(defn score-round [[p1 p2]]
  (+ (shape p2) (get-in outcomes [p1 p2])))

(defn score [game]
  (reduce + (map score-round game)))

(defn score-round2 [[p1 p2]]
  (+ (shape p2) (get-in outcomes [p1 (get-in outcomes2 [p1 p2])])))

(defn score2 [game]
  (reduce + (map score-round2 game)))

(comment
  (score (parse sample))
  (score (parse data)))
