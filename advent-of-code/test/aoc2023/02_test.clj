(ns aoc2023.02-test
  (:require
   [aoc2023.02 :refer [max-colors parse-game power sample-data sample-rules
                       solve valid? solve2]]
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

(deftest max-colors-test
  (testing "Game 1"
    (is (= {:red 4 :green 2 :blue 6} (max-colors [1 {:red '(4 1)
                                                     :blue '(3 6)
                                                     :green '(2 2)}]))))
  (testing "Game 3"
    (is (= {:red 20 :green 13 :blue 6} (max-colors [3 {:red '(20 4 1)
                                                       :blue '(6 5)
                                                       :green '(8 13 5)}])))))
(deftest power-test
  (testing "Game 1"
    (is (= 48 (power {:red 4 :green 2 :blue 6}))))
  (testing "Game 3"
    (is (= 1560 (power {:red 20 :green 13 :blue 6})))))

(deftest solve1-sample-test
  (is (= 8 (solve sample-rules sample-data))))

(deftest solve2-sample-test
  (is (= 2286 (solve2 sample-data))))
