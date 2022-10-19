(ns aoc2015.06.set-test
  (:require
   [clojure.test :refer [deftest is]]
   [aoc2015.06.set :refer [draw turn-on turn-off get-on get-off toggle]]))

(def test-grid
  #{[0 0] [0 1] [0 2]
    [1 0] [1 1] [1 2]
    [2 0] [2 1] [2 2]})

(deftest square-test
  (is (= test-grid
         (draw [0 0] [2 2]))))

(deftest turn-on-test
  (is (= #{}
         (turn-on #{} #{})))
  (is (= test-grid
         (turn-on #{} test-grid)))
  (is (= test-grid
         (turn-on test-grid test-grid)))
  (is (= test-grid
         (turn-on #{[0 0]} test-grid))))

(deftest turn-off-test
  (is (= #{}
         (turn-off #{} #{})))
  (is (= #{}
         (turn-off test-grid test-grid)))
  (is (= #{[1 0] [1 1] [1 2]
           [2 0] [2 1] [2 2]}
         (turn-off test-grid #{[0 0] [0 1] [0 2]}))))

(deftest get-on-test
  (is (= test-grid
         (get-on test-grid test-grid)))
  (is (= #{[0 2] [1 2] [2 2]}
         (get-on test-grid #{[0 2] [0 3]
                             [1 2] [1 3]
                             [2 2] [2 3]}))))

(deftest getoff-test
  (is (= #{[0 3] [1 3] [2 3]}
         (get-off test-grid #{[0 2] [0 3]
                              [1 2] [1 3]
                              [2 2] [2 3]}))))

(deftest toggle-test
  (is (= test-grid
         (toggle #{} test-grid)) "No state should turn on all")
  (is (= #{}
         (toggle test-grid test-grid)) "Same state should turn everything off"))
