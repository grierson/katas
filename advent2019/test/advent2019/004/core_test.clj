(ns advent2019.004.core-test
  (:require [advent2019.004.core :refer :all]
            [midje.sweet :refer :all]))

(fact "top down"
 (valid? ..pass..) => true
 (provided
  (six? ..pass..) => true
  (pair? ..pass..) => true
  (increase? ..pass..) => true))

(facts "Given examples"
       (fact "111111 is valid"
             (valid? 111111) => true)
       (fact "223450 is invalid because decreasing 50"
             (valid? 223450) => false)
       (fact "123789 is invalid because no pair"
             (valid? 123789) => false))

(tabular
 (fact "six? => truth"
       (six? ?pass) => true)
 ?pass
 123456)

(tabular
 (fact "six? => false"
       (six? ?pass) => false)
 ?pass
 nil
 1
 12
 123
 1234
 12345
 1234567)


(tabular
 (fact "pair? => true"
       (pair? ?pass) => true)
 ?pass
 11)

(tabular
 (fact "pair? => false"
       (pair? ?pass) => false)
 ?pass
 12)

(tabular
 (fact "increase? => true"
       (increase? ?pass) => true)
 ?pass
 12
 123
 1234
 12345
 11111)

(tabular
 (fact "increase? => false"
       (increase? ?pass) => false)
 ?pass
 21
 321
 212)
