(ns tic-tac-toe.core-test
  (:require [midje.sweet :refer :all]
            [tic-tac-toe.core :refer :all]))

(fact "degenerate case"
  (score nil) => nil)

(facts "top row"
  (fact "X wins"
    (score [[\X \X \X]
            [\_ \_ \_]
            [\_ \_ \_]]) => \X)
  (fact "O wins"
    (score [[\O \O \O]
            [\_ \_ \_]
            [\_ \_ \_]]) => \O))
