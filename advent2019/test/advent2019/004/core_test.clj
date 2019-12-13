(ns advent2019.004.core-test
  (:require [advent2019.004.core :refer :all]
            [midje.sweet :refer :all]))

(fact "top down"
 (valid? ..pass..) => true
 (provided
  (six? ..pass..) => true
  (pair? ..pass..) => true
  (increase? ..pass..) => true))

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
