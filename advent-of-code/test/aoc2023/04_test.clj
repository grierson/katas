(ns aoc2023.04-test
  (:require
   [aoc2023.04 :refer [make-results parse process sample-data score score2
                       solve solve2]]
   [clojure.test :refer [are deftest is]]))

(deftest parse-test
  (are [x y] (= x (parse y))
    {1 [#{41 48 83 86 17} #{83 86 6 31 17 9 48 53}]}  "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53"
    {2 [#{13 32 20 16 61} #{61 30 68 82 17 32 24 19}]}  "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19"
    {3 [#{1 21 53 59 44} #{69 82 63 72 16 21 14  1}]}  "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1"
    {4 [#{41 92 73 84 69} #{59 84 76 51 58  5 54 83}]}  "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83"
    {5 [#{87 83 26 28 32} #{88 30 70 12 93 22 82 36}]}  "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36"
    {6 [#{31 18 13 56 72} #{74 77 10 23 35 67 36 11}]}  "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"))

(deftest score-test
  (are [x y] (= x (score y))
    8 [#{41 48 83 86 17} #{83 86 6 31 17 9 48 53}]
    2 [#{13 32 20 16 61} #{61 30 68 82 17 32 24 19}]
    2 [#{1 21 53 59 44} #{69 82 63 72 16 21 14  1}]
    1 [#{41 92 73 84 69} #{59 84 76 51 58  5 54 83}]
    0 [#{87 83 26 28 32} #{88 30 70 12 93 22 82 36}]
    0 [#{31 18 13 56 72} #{74 77 10 23 35 67 36 11}]))

(deftest solve-test
  (is (= 13 (solve sample-data))))

(deftest score2-test
  (are [x y] (= x (score2 y))
    [2 3 4 5] [1 [#{41 48 83 86 17} #{83 86 6 31 17 9 48 53}]]
    [3 4] [2 [#{13 32 20 16 61} #{61 30 68 82 17 32 24 19}]]
    [4 5] [3 [#{1 21 53 59 44} #{69 82 63 72 16 21 14  1}]]
    [5] [4 [#{41 92 73 84 69} #{59 84 76 51 58  5 54 83}]]
    [] [5 [#{87 83 26 28 32} #{88 30 70 12 93 22 82 36}]]
    [] [6 [#{31 18 13 56 72} #{74 77 10 23 35 67 36 11}]]))

(deftest make-results-test
  (are [x y] (= x (make-results y))
    {1 [2 3 4 5]
     2 [3 4]
     3 [4 5]
     4 [5]
     5 []
     6 []} {1 [#{41 48 83 86 17} #{83 86 6 31 17 9 48 53}]
            2 [#{13 32 20 16 61} #{61 30 68 82 17 32 24 19}]
            3 [#{1 21 53 59 44} #{69 82 63 72 16 21 14  1}]
            4 [#{41 92 73 84 69} #{59 84 76 51 58  5 54 83}]
            5 [#{87 83 26 28 32} #{88 30 70 12 93 22 82 36}]
            6 [#{31 18 13 56 72} #{74 77 10 23 35 67 36 11}]}))

(deftest process-test
  (is (= {1 1
          2 2
          3 4
          4 8
          5 14
          6 1} (process {1 [2 3 4 5]
                         2 [3 4]
                         3 [4 5]
                         4 [5]
                         5 []
                         6 []}))))

(deftest solve2-test
  (is (= 30 (solve2 sample-data))))
