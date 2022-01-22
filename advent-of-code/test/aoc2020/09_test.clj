(ns aoc2020.09-test
  (:require [clojure.test :refer :all]
            [aoc2020.09 :refer [generate-pair-sums solve1]]))


(deftest sample-test
  (let [sample "35\n20\n15\n25\n47\n40\n62\n55\n65\n95\n102\n117\n150\n182\n127\n219\n299\n277\n309\n576"]
    (is (= (solve1 5 sample) 127))))

