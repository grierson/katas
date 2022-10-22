(ns aoc2016.01-test
  (:require [clojure.test :refer [deftest is testing]]
            [aoc2016.01 :refer [make-state default solve trace-move visited?]]))

(deftest problem1-test
  (is (= 5
         (solve default [[\R 2] [\L 3]])))
  (is (= 2
         (solve default [[\R 2] [\R 2] [\R 2]])))
  (is (= 12
         (solve default [[\R 5] [\L 5] [\R 5] [\R 3]]))))

(deftest trace-steps-test
  (testing "north"
    (is (= [[0 1]]
           (trace-move default 1))))

  (testing "south"
    (is (= [[0 -1]]
           (trace-move (make-state {:facing \S}) 1))))

  (testing "east"
    (is (= [[1 0]]
           (trace-move (make-state {:facing \E}) 1))))

  (testing "west"
    (is (= [[-1 0]]
           (trace-move (make-state {:facing \W}) 1)))))

(deftest visited-test
  (testing "not visited"
    (is (nil? (visited? #{} [[0 0] [0 1]]))))
  (testing "visited"
    (is (= [0 1]
           (visited? #{[0 1]} [[0 0] [0 1]])))))
