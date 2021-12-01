(ns aoc2016.01-test
  (:require [clojure.test :refer :all]
            [aoc2016.01 :refer [solve move-log]]))

(def start {:direction \N :x 0 :y 0})

(deftest problem1-test
  (is (= 5
         (solve start [[\R 2] [\L 3]])))
  (is (= 2
         (solve start [[\R 2] [\R 2] [\R 2]])))
  (is (= 12
         (solve start [[\R 5] [\L 5] [\R 5] [\R 3]]))))


(deftest steps-log-test
  (is (= #{[0 0] [0 1] [0 2]}
         (move-log start 2))))