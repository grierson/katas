(ns tic-tac-toe.core-test
  (:require [midje.sweet :refer :all]
            [tic-tac-toe.core :refer :all]))

(fact "degenerate case"
  (score nil) => nil)

(fact "top row"
  (score [[\X \X \X]
          [\_ \_ \_]
          [\_ \_ \_]]) => \X)