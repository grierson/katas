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
 (fact "six? => truthy"
       (six? ?pass) => true)
 ?pass
 111111)

(tabular
 (fact "six? => falsey"
       (six? ?pass) => false)
 ?pass
 1)
