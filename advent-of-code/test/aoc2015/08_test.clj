(ns aoc2015.08-test
  (:require
   [aoc2015.08 :refer [size]]
   [clojure.test :refer [deftest is testing]]))

(deftest literal-memory-test
  (testing "examples"
    (is (= (size "")
           {:memory 0
            :literal 2}))
    (is (= (size "abc")
           {:memory 3
            :literal 5}))
    (is (= (size "aaa\"aaa")
           {:memory 7
            :literal 10}))))
