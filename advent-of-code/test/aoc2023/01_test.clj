(ns aoc2023.01-test
  (:require [clojure.test :refer [deftest is testing]]
            [aoc2023.01 :refer [parse]]))

(deftest parse-test
  (testing "first example"
    (is (= (parse "1abc2") 12)))
  (testing "second example"
    (is (= (parse "pqr3stu8vwx") 38)))
  (testing "thrid example"
    (is (= (parse "a1b2c3d4e5f") 15)))
  (testing "fourth example"
    (is (= (parse "treb7uchet") 77))))
