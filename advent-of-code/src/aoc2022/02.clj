(ns aoc2022.02
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def sample
  "A Y
B X
C Z")

(def data (slurp (io/resource "aoc2022/02.txt")))

(def opponent
  {\A :ROCK
   \B :PAPER
   \C :SCISSORS})

(def decipher
  (merge
   opponent
   {\X :ROCK
    \Y :PAPER
    \Z :SCISSORS}))

(def decipher2
  (merge
   opponent
   {\X :LOSE
    \Y :DRAW
    \Z :WIN}))

(defn parse-fn [decipher-fn game]
  (map
   (fn [round]
     (let [[p1 _ p2] round]
       [(decipher-fn p1) (decipher-fn p2)]))
   (str/split-lines game)))

(def parse (partial parse-fn decipher))
(def parse2 (partial parse-fn decipher2))

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
  (+ (shape p2)
     (get-in outcomes [p1 p2])))

(defn score-round2 [[p1 p2]]
  (let [expected-outcome (get-in outcomes2 [p1 p2])]
    (+ (shape expected-outcome)
       (get-in outcomes [p1 expected-outcome]))))

(defn score-fn [score-round-fn game]
  (reduce + (map score-round-fn game)))

(def score (partial score-fn score-round))
(def score2 (partial score-fn score-round2))

(comment
  (score (parse sample))
  (score (parse data))
  (score2 (parse2 sample))
  (score2 (parse2 data)))
