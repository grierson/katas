(ns tic-tac-toe.core-test
  (:require [midje.sweet :refer :all]
            [tic-tac-toe.core :refer :all]))

(fact "degenerate case"
  (score nil) => nil)

(fact "new game"
  (score [[\_ \_ \_]
          [\_ \_ \_]
          [\_ \_ \_]]) => nil)

(facts "top row"
  (fact "X wins"
    (score [[\X \X \X]
            [\_ \_ \_]
            [\_ \_ \_]]) => \X)
  (fact "O wins"
    (score [[\O \O \O]
            [\_ \_ \_]
            [\_ \_ \_]]) => \O))

(facts "middle row"
  (fact "X wins"
    (score [[\_ \_ \_]
            [\X \X \X]
            [\_ \_ \_]]) => \X)
  (fact "O wins"
    (score [[\_ \_ \_]
            [\O \O \O]
            [\_ \_ \_]]) => \O))

(facts "bottom row"
  (fact "X wins"
    (score [[\_ \_ \_]
            [\_ \_ \_]
            [\X \X \X]]) => \X)
  (fact "O wins"
    (score [[\_ \_ \_]
            [\_ \_ \_]
            [\O \O \O]]) => \O))

(facts "left column"
  (fact "X wins"
    (score [[\X \_ \_]
            [\X \_ \_]
            [\X \_ \_]]) => \X))
