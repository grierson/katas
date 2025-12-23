(ns aoc2025.03-test
  (:require
   [clojure.test :refer [deftest is]]
   [aoc2025.03 :as subject]))

(deftest find-joltage-test
  (is (= [0 9]
         (subject/find-next-joltage "987654321111111" 9 0))))
