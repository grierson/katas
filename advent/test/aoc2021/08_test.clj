(ns aoc2021.08-test
  (:require [clojure.test :refer :all])
  (:require [aoc2021.08 :refer :all]))


(deftest is1478?-test
  (testing "1 segments is false"
    (is (nil? (is1478? "a"))))
  (testing "2 segments is one"
    (is (= 1 (is1478? "ab"))))
  (testing "3 segments is seven"
    (is (= 7 (is1478? "abc"))))
  (testing "4 segments is four"
    (is (= 4 (is1478? "abcd"))))
  (testing "5 segments is false"
    (is (nil? (is1478? "abcde"))))
  (testing "6 segments is false"
    (is (nil? (is1478? "abcdef"))))
  (testing "7 segments is eight"
    (is (= 8 (is1478? "abcdefg")))))

(deftest solve-test
  (testing "count all 1 4 7 8 instances"
    (is (= 26
           (solve sample)))))
