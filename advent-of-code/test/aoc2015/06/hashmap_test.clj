(ns aoc2015.06.hashmap-test
  (:require [clojure.test :refer [deftest is testing]]
            [aoc2015.06.hashmap :refer [turn-on turn-off toggle]]))

(deftest turn-on-test
  (testing "Creates key"
    (is (= {[0 0] 1}
           (turn-on {} [0 0]))))
  (testing "Add one to key"
    (is (= {[0 0] 2}
           (turn-on {[0 0] 1} [0 0])))))

(deftest turn-off-test
  (testing "Creates key"
    (is (= {[0 0] 0}
           (turn-off {} [0 0]))))
  (testing "Decrements key"
    (is (= {[0 0] 0}
           (turn-off {[0 0] 1} [0 0]))))
  (testing "Doesn't go below zero"
    (is (= {[0 0] 0}
           (turn-off {[0 0] 0} [0 0])))))

(deftest toggle-test
  (testing "Creates key"
    (is (= {[0 0] 2}
           (toggle {} [0 0]))))
  (testing "Add two to key"
    (is (= {[0 0] 4}
           (toggle {[0 0] 2} [0 0])))))
