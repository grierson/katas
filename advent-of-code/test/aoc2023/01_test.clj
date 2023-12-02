(ns aoc2023.01-test
  (:require [clojure.test :refer [deftest is testing are]]
            [aoc2023.01 :refer [parse parse2 find-first find-last]]))

(deftest parse-test
  (testing "first example"
    (is (= (parse "1abc2") 12)))
  (testing "second example"
    (is (= (parse "pqr3stu8vwx") 38)))
  (testing "thrid example"
    (is (= (parse "a1b2c3d4e5f") 15)))
  (testing "fourth example"
    (is (= (parse "treb7uchet") 77))))

(deftest find-first-test
  (are [x y] (= y (find-first x))
    "two1nine" 2
    "eightwothree" 8
    "abcone2threexyz" 1
    "xtwone3four" 2
    "4nineeightseven2" 4
    "zoneight234" 1
    "7pqrstsixteen" 7))

(deftest find-last-test
  (are [x y] (= y (find-last x))
    "two1nine" 9
    "eightwothree" 3
    "abcone2threexyz" 3
    "xtwone3four" 4
    "4nineeightseven2" 2
    "zoneight234" 4
    "7pqrstsixteen" 6))

(deftest parse2-test
  (are [x y] (= y (parse2 x))
    "two1nine" 29
    "eightwothree" 83
    "abcone2threexyz" 13
    "xtwone3four" 24
    "4nineeightseven2" 42
    "zoneight234" 14
    "7pqrstsixteen" 76))
