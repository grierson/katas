(ns aoc2023.05-test
  (:require
   [aoc2023.05 :refer [parse sample]]
   [clojure.test :refer [are deftest is]]))

(deftest parse-test
  (let [result (parse sample)]
    (is (= [79 14 55 13]
           (:seeds result)))))
