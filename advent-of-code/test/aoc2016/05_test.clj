(ns aoc2016.05-test
  (:require
   [aoc2016.05 :refer [solve solve2]]
   [clojure.test :refer [deftest is]]))

(deftest find-hash-test
  (is (= (solve "abc") "18f47a30"))
  (is (= (solve2 "abc") "05ace8e3")))
