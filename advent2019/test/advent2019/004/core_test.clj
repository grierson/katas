(ns advent2019.004.core-test
  (:require [advent2019.004.core :refer :all]
            [midje.sweet :refer :all]))

(fact "top down"
 (valid? ..pass..) => true
 (provided
  (six? ..pass..) => true
  (pair? ..pass..) => true
  (increase? ..pass..) => true))
