(ns money.core-test
  (:require [midje.sweet :refer :all]
            [money.core :refer :all]))

(fact "make money"
  (make-money) => {:amount   0
                   :currency :usd})

(facts "multiplier"
  (fact "multiply five dollars"
    (let [money (make-money 5)]
      (*$ money 2) => (make-money 10)
      (*$ money 3) => (make-money 15)))
  (fact "multiply five euro"
    (let [money (make-money 5 :gbp)]
      (*$ money 2) => (make-money 10 :gbp)
      (*$ money 3) => (make-money 15 :gbp))))

(facts "addition"
  (fact "add 5 usd to 5 usd is 10 usd"
    (+$ (make-money 5) (make-money 5)) => (make-money 10)))