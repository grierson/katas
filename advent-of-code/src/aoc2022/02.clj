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

(defn parse-fn [decyher-fn game]
  (map
   (fn [round]
     (let [[p1 _ p2] round]
       [(decypher-fn p1) (decypher-fn p2)]))
   (str/split-lines game)))

(def parse (partial parse-fn decypher))
(def parse2 (partial parse-fn decypher2))

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
