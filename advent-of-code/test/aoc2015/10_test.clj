(ns aoc2015.10-test
  (:require [clojure.test :refer [deftest is testing]]
            [aoc2015.10 :refer [turn]]))

(deftest turn-test
  (is (= "11" (turn "1")))
  (is (= "21" (turn "11"))))
