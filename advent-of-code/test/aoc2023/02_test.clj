(ns aoc2023.02-test
  (:require
   [aoc2023.02 :refer [parse-game sample-rules sample-data solve]]
   [clojure.test :refer [deftest is testing]]))

(deftest parse-test
  (testing "Game 1"
    (is (= {1 true}
           (parse-game sample-rules "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"))))
  (testing "Game 3"
    (is (= {3 false}
           (parse-game sample-rules "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red")))))

(deftest solve1-sample-test
  (is (= 8 (solve sample-rules sample-data))))
