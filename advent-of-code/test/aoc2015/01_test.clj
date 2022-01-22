(ns aoc2015.01-test
  (:require [clojure.test :refer :all])
  (:require [aoc2015.01 :refer :all]))

(deftest examples-test
  (testing "zero floors"
    (is (= (count-floors "(())") 0))
    (is (= (count-floors "()()") 0)))
  (testing "Up three floors"
    (is (= (count-floors "(((") 3))
    (is (= (count-floors "(()(()(") 3))
    (is (= (count-floors "))(((((") 3)))
  (testing "Down 1 floor floors"
    (is (= (count-floors "())") -1))
    (is (= (count-floors "))(") -1)))
  (testing "Down 3 floor floors"
    (is (= (count-floors ")))") -3))
    (is (= (count-floors ")())())") -3))))

(deftest basement-test
  (is (= (basement ")") 1))
  (is (= (basement "()())") 5)))

(deftest update-state-test
  (testing "first instruction sends you to the basement"
    (is (= (update-state {:floor 0 :pc 0} \))
           1)))
  (testing "instruction moves up one floor and updates pc"
    (is (= (update-state {:floor 0 :pc 0} \()
           {:floor 1
            :pc    1}))))
