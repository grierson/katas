(ns aoc2022.05-test
  (:require [clojure.test :refer [deftest is]]
            [aoc2022.05 :refer [sample
                                sample-crates
                                parse
                                procedure
                                ends]]))

(deftest parse-test
  (is (= [[1 2 1]
          [3 1 3]
          [2 2 1]
          [1 1 2]]
         (parse sample))))

(deftest procedure-test
  (is (= {1 [\Z \N \D]
          2 [\M \C]
          3 [\P]}
         (procedure sample-crates [1 2 1])))
  (is (= {1 []
          2 [\M \C]
          3 [\P \D \N \Z]}
         (procedure {1 [\Z \N \D]
                     2 [\M \C]
                     3 [\P]}
                    [3 1 3])))
  (is (= {1 [\C \M]
          2 []
          3 [\P \D \N \Z]}
         (procedure {1 []
                     2 [\M \C]
                     3 [\P \D \N \Z]}
                    [2 2 1])))
  (is (= {1 [\C]
          2 [\M]
          3 [\P \D \N \Z]}
         (procedure {1 [\C \M]
                     2 []
                     3 [\P \D \N \Z]}
                    [1 1 2]))))

(deftest ends-test
  (is (= "CMZ"
         (ends {1 [\C]
                2 [\M]
                3 [\P \D \N \Z]}))))
