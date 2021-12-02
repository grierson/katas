(ns aoc2016.01-test
  (:require [clojure.test :refer :all]
            [aoc2016.01 :refer [make-state default solve trace-steps visited?]]))

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
           (trace-steps default 1))))

  (testing "south"
    (is (= [[0 -1]]
           (trace-steps (make-state {:direction \S}) 1))))

  (testing "east"
    (is (= [[1 0]]
           (trace-steps (make-state {:direction \E}) 1))))

  (testing "west"
    (is (= [[-1 0]]
           (trace-steps (make-state {:direction \W}) 1)))))

(deftest visited-test
  (testing "not visited"
    (is (nil? (visited? #{} [[0 0] [0 1]]))))
  (testing "visited"
    (is (= [0 1]
           (visited? #{[0 1]} [[0 0] [0 1]])))))
