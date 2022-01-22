(ns aoc2021.01-test
  (:require [clojure.test :refer :all]
            [aoc2021.01 :refer [scan scan2]]))


(deftest problem-1-case
  (is (= 7
         (scan [199 200 208 210 200 207 240 269 260 263]))))

(deftest problem-2-case
  (is (= 5
         (scan2 [199 200 208 210 200 207 240 269 260 263]))))
