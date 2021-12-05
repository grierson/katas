(ns aoc2021.03-test
  (:require [clojure.test :refer :all]
            [aoc2021.03 :refer :all]))

(deftest binary-str->list-test
  (testing "convert binary string to list"
    (is (= '(0 0 1 0 0)
           (number->list "00100")))))

(deftest report->data-test
  (testing "convert diagnostic report to data"
    (is (= [[0 0 1 0 0]
            [1 1 1 1 0]
            [1 0 1 1 0]
            [1 0 1 1 1]
            [1 0 1 0 1]
            [0 1 1 1 1]
            [0 0 1 1 1]
            [1 1 1 0 0]
            [1 0 0 0 0]
            [1 1 0 0 1]
            [0 0 0 1 0]
            [0 1 0 1 0]]
           (report->data sample)))))

(def sample-report (report->data sample))

(deftest read-column-test
  (testing "read first column"
    (is (= [0 1 1 1 1 0 0 1 1 1 0 0]
           (read-column 0 sample-report))))

  (testing "read second column"
    (is (= [0 1 0 0 0 1 0 1 0 1 0 1]
           (read-column 1 sample-report))))

  (testing "read last column"
    (is (= [0 0 0 1 1 1 1 0 0 1 0 0]
           (read-column 4 sample-report)))))

(deftest get-gamma-bit-test
  (testing "gamma first column"
    (is (= 1
           (get-gamma-bit (read-column 0 sample-report)))))

  (testing "gamma second column"
    (is (= 0
           (get-gamma-bit (read-column 1 sample-report)))))

  (testing "gamma third column"
    (is (= 1
           (get-gamma-bit (read-column 2 sample-report)))))

  (testing "gamma fourth column"
    (is (= 1
           (get-gamma-bit (read-column 3 sample-report)))))

  (testing "gamma fifth column"
    (is (= 0
           (get-gamma-bit (read-column 4 sample-report))))))


(deftest gamma-test
  (testing "sample-report"
    (is (= [1 0 1 1 0]
           (gamma sample-report)))))

(deftest binary->decimal-test
  (testing "gamma sample-report"
    (is (= 22
           (binary->decimal "10110"))))

  (testing "epsilon sample-report"
    (is (= 9
           (binary->decimal "01001")))))

(deftest epsilon-test
  (testing "sample-report"
    (is (= [0 1 0 0 1]
           (epsilon sample-report)))))

(deftest solve-test
  (testing "sample-report"
    (is (= 198
           (solve sample)))))