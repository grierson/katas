(ns aoc2025.01-test
  (:require
   [aoc2025.01 :refer [count-clicks parse-line rotation]]
   [clojure.test :refer [are deftest is testing]]))

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

((requiring-resolve 'hashp.install/install!))

(deftest count-clicks-test
  (testing "right full turns plus edge case"
    (is (= 2 (count-clicks 1 {:direction :right :amount 199}))))
  (testing "left full turns plus edge case"
    (is (= 2 (count-clicks 1 {:direction :left :amount 101}))))
  (testing "edge cases"
    (are [expected current input] (= expected (count-clicks current input))
      0 0 {:direction :right :amount 1}
      1 0 {:direction :right :amount 100}
      1 0 {:direction :left :amount 100}
      2 1 {:direction :right :amount 199}
      2 99 {:direction :right :amount 101}
      1 99 {:direction :left :amount 101}))
  (testing "examples"
    (are [expected current input] (= expected (count-clicks current input))
      1 50 {:direction :left :amount 68}
      0 82 {:direction :left :amount 30}
      1 52 {:direction :right :amount 48}
      0 0 {:direction :left :amount 5}
      1 95 {:direction :right :amount 60}
      1 55 {:direction :left :amount 55}
      0 0 {:direction :left :amount 1}
      1 99 {:direction :left :amount 99}
      0 0 {:direction :right :amount 14}
      1 14 {:direction :left :amount 82})))
