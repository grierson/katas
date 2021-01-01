(ns tic-tac-toe.core-test
  (:require [midje.sweet :refer :all]
            [tic-tac-toe.core :refer :all]))

(fact "degenerate case"
  (score nil) => nil)

(facts "top row"
  (fact "X wins"
    (score [[\X \X \X]
            [\O \X \O]
            [\O \_ \O]]) => \X)
  (fact "O wins"
    (score [[\O \O \O]
            [\X \X \_]
            [\X \X \_]]) => \O))

(facts "middle row"
  (fact "X wins"
    (score [[\O \O \X]
            [\X \X \X]
            [\_ \_ \_]]) => \X)
  (fact "O wins"
    (score [[\O \O \X]
            [\O \O \O]
            [\_ \_ \_]]) => \O))
