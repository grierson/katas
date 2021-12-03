(ns aoc2021.03-test
  (:require [clojure.test :refer :all]
            [aoc2021.03 :refer :all]))


(deftest read-column-test
  (testing "read first column"
    (is (= [0 1 1 1 1 0 0 1 1 1 0 0]
           (read-column 0 sample))))

  (testing "read second column"
    (is (= [0 1 0 0 0 1 0 1 0 1 0 1]
           (read-column 1 sample))))

  (testing "read last column"
    (is (= [0 0 0 1 1 1 1 0 0 1 0 0]
           (read-column 4 sample)))))

(deftest get-gamma-bit-test
  (testing "gamma first column"
    (is (= 1
           (get-gamma-bit (read-column 0 sample)))))

  (testing "gamma second column"
    (is (= 0
           (get-gamma-bit (read-column 1 sample)))))

  (testing "gamma third column"
    (is (= 1
           (get-gamma-bit (read-column 2 sample)))))

  (testing "gamma fourth column"
    (is (= 1
           (get-gamma-bit (read-column 3 sample)))))

  (testing "gamma fifth column"
    (is (= 0
           (get-gamma-bit (read-column 4 sample))))))


(deftest gamma-test
  (testing "sample"
    (is (= "10110"
           (gamma sample)))))

(deftest binary->decimal-test
  (testing "gamma sample"
    (is (= 22
           (binary->decimal "10110"))))

  (testing "epsilon sample"
    (is (= 9
           (binary->decimal "01001")))))

(deftest epsilon-test
  (testing "sample"
    (is (= "01001"
           (epsilon sample)))))

(deftest solve-test
  (testing "sample"
    (is (= 198
           (solve sample)))))