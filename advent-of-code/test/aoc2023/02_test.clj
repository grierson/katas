(ns aoc2023.02-test
  (:require
   [aoc2023.02 :refer [parse-game valid? sample-rules sample-data solve]]
   [clojure.test :refer [deftest is testing]]))

(deftest parse-test
  (testing "Game 1"
    (is (= [1 {:red '(4 1)
               :blue '(3 6)
               :green '(2 2)}]
           (parse-game "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"))))
  (testing "Game 3"
    (is (= [3 {:red '(20 4 1)
               :blue '(6 5)
               :green '(8 13 5)}]
           (parse-game "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red")))))

(deftest valid-test
  (testing "Game 1"
    (is (true? (valid? sample-rules [1 {:red '(4 1)
                                        :blue '(3 6)
                                        :green '(2 2)}]))))
  (testing "Game 3"
    (is (false? (valid? sample-rules [3 {:red '(20 4 1)
                                         :blue '(6 5)
                                         :green '(8 13 5)}])))))

(deftest solve1-sample-test
  (is (= 8 (solve sample-rules sample-data))))
