(ns aoc2025.01-test
  (:require
   [aoc2025.01 :refer [parse-line rotation]]
   [clojure.test :refer [are deftest testing]]))

(deftest parse-line-test
  (testing "examples"
    (are [x y] (= (parse-line x) y)
      "L68" {:direction :left :amount 68}
      "L30" {:direction :left :amount 30}
      "R48" {:direction :right :amount 48}
      "L5" {:direction :left :amount 5}
      "R60" {:direction :right :amount 60}
      "L55" {:direction :left :amount 55}
      "L1" {:direction :left :amount 1}
      "L99" {:direction :left :amount 99}
      "R14" {:direction :right :amount 14}
      "L82" {:direction :left :amount 82})))

(deftest rotation-test
  (testing "examples"
    (are [expected current input] (= expected (rotation current input))
      82 50 {:direction :left :amount 68}
      52 82 {:direction :left :amount 30}
      0 52 {:direction :right :amount 48}
      95 0 {:direction :left :amount 5}
      55 95 {:direction :right :amount 60}
      0 55 {:direction :left :amount 55}
      99 0 {:direction :left :amount 1}
      0 99 {:direction :left :amount 99}
      14 0 {:direction :right :amount 14}
      32 14 {:direction :left :amount 82}))

  (testing "edge cases"
    (are [expected current input] (= expected (rotation current input))
      1 0 {:direction :right :amount 1}
      99 0 {:direction :left :amount 1}
      0 99 {:direction :right :amount 1})))
