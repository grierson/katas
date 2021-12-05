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
  (testing "gamma sample"
    (is (= 22
           (binary->decimal "10110"))))

  (testing "epsilon sample"
    (is (= 9
           (binary->decimal "01001"))))

  (testing "oxygen sample"
    (is (= 23
           (binary->decimal "10111"))))

  (testing "co2 sample"
    (is (= 10
           (binary->decimal "01010")))))

(deftest epsilon-test
  (testing "sample-report"
    (is (= [0 1 0 0 1]
           (epsilon sample-report)))))

(deftest solve-test
  (testing "sample-report"
    (is (= 198
           (solve sample)))))


(deftest oxygen-test
  (let [o1 [[1 1 1 1 0]
            [1 0 1 1 0]
            [1 0 1 1 1]
            [1 0 1 0 1]
            [1 1 1 0 0]
            [1 0 0 0 0]
            [1 1 0 0 1]]
        o2 [[1 0 1 1 0]
            [1 0 1 1 1]
            [1 0 1 0 1]
            [1 0 0 0 0]]
        o3 [[1 0 1 1 0]
            [1 0 1 1 1]
            [1 0 1 0 1]]
        o4 [[1 0 1 1 0]
            [1 0 1 1 1]]
        o5 [[1 0 1 1 1]]]

    (testing "First column has more ones so filter all zeros"
      (is (= o1
             (oxygen sample-report 0))))

    (testing "Second column has more zero so filter the ones"
      (is (= o2
             (oxygen o1 1))))

    (testing "Third column has more ones so filter the zeros"
      (is (= o3
             (oxygen o2 2))))

    (testing "Forth column has more ones so filter the zeros"
      (is (= o4
             (oxygen o3 3))))

    (testing "Fifth column has even amount of ones and zeros so only keeps ones"
      (is (= o5
             (oxygen o4 4))))))


(deftest co2-test
  (let [c1 [[0 0 1 0 0]
            [0 1 1 1 1]
            [0 0 1 1 1]
            [0 0 0 1 0]
            [0 1 0 1 0]]
        c2 [[0 1 1 1 1]
            [0 1 0 1 0]]
        c3 [[0 1 0 1 0]]]
    (testing "First column has less zeros so filter ones"
      (is (= c1
             (co2 sample-report 0))))
    (testing "Second column has fewer 1 bits so only keep the ones"
      (is (= c2
             (co2 c1 1))))
    (testing "Third column has equal amount so filter the ones"
      (is (= c3
             (co2 c2 2))))))

(deftest get-oxygen-test
  (is (= [1 0 1 1 1]
         (get-oxygen sample-report))))

(deftest get-co2-test
  (is (= [0 1 0 1 0]
         (get-co2 sample-report))))

(deftest solve-test
  (is (= 230
         (solve2 sample-report))))