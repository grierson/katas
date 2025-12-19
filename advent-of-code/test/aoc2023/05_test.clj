(ns aoc2023.05-test
  (:require
   [aoc2023.05 :refer [parse get-id sample]]
   [clojure.test :refer [deftest is]]))

(deftest parse-test
  (let [result (parse sample)]
    (is (= [79 14 55 13]
           (:seeds result)))))

(deftest within-range-test
  (is (nil? (get-id [50 98 2] 79)))
  (is (= 81 (get-id [52 50 48] 79))))
