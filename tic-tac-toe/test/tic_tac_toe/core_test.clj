(ns tic-tac-toe.core-test
  (:require [midje.sweet :refer :all]
            [tic-tac-toe.core :refer :all]))

(fact "degenerate case"
  (score nil) => nil)

(fact "new game"
  (score [[\_ \_ \_]
          [\_ \_ \_]
          [\_ \_ \_]]) => nil)

(facts "rows"
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
              [\O \O \O]]) => \O)))

(facts "columns"
  (facts "left column"
    (fact "X wins"
      (score [[\X \_ \_]
              [\X \_ \_]
              [\X \_ \_]]) => \X)
    (fact "O wins"
      (score [[\O \_ \_]
              [\O \_ \_]
              [\O \_ \_]]) => \O))

  (facts "middle column"
    (fact "X wins"
      (score [[\_ \X \_]
              [\_ \X \_]
              [\_ \X \_]]) => \X)
    (fact "O wins"
      (score [[\_ \O \_]
              [\_ \O \_]
              [\_ \O \_]]) => \O))

  (facts "right column"
    (fact "X wins"
      (score [[\_ \_ \X]
              [\_ \_ \X]
              [\_ \_ \X]]) => \X)
    (fact "O wins"
      (score [[\_ \_ \O]
              [\_ \_ \O]
              [\_ \_ \O]]) => \O)))

(facts "diagonal"
  (fact "X is in Top left, Middle, and Bottom right is winner"
    (score [[\X \_ \_]
            [\_ \X \_]
            [\_ \_ \X]]) => \X))