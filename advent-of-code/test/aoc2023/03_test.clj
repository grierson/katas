(ns aoc2023.03-test
  (:require
   [aoc2023.03 :refer [parse-icons
                       parse-line-numbers
                       get-surrounding-number
                       get-surrounding-numbers
                       sample-data
                       surround-locations
                       solve
                       solve2]]
   [clojure.string :as str]
   [clojure.test :refer [deftest is testing]]))

(deftest parse-line-numbers-test
  (testing "get numbers row 0"
    (is (= {[0 2] 467
            [5 7] 114}
           (parse-line-numbers "467..114.."))))
  (testing "get numbers row 1"
    (is (= {}
           (parse-line-numbers "...*......"))))
  (testing "handle duplicate number (4 in 24 not read twice)"
    (is (= {[1 2] 24 [5 5] 4}
           (parse-line-numbers ".24..4..")))))

(deftest get-icons-test
  (testing "get numbers row 1"
    (is (= '([1 3] [3 6] [4 3] [5 5] [8 3] [8 5])
           (parse-icons (str/split-lines sample-data))))))

(deftest get-surround
  (testing "1, 1"
    (is
     (= [[0 0] [0 1] [0 2]
         [1 0] [1 2]
         [2 0] [2 1] [2 2]]
        (surround-locations [1 1])))))

(deftest get-surrounding-number-test
  (testing "first * in sample"
    (is (= {[0 [0 2]] 467}
           (get-surrounding-number
            {0 {[0 2] 467 [5 7] 114}
             1 {}
             2 {[2 3] 35 [6 8] 633}}
            [0 2])))))

(deftest get-surrounding-numbers-test
  (testing "first * in sample"
    (is (= {[1 3] {[0 [0 2]] 467 [2 [2 3]] 35}}
           (get-surrounding-numbers
            {0 {[0 2] 467 [5 7] 114}
             1 {}
             2 {[2 3] 35 [6 8] 633}}
            [1 3])))))

(deftest solve-test
  (is (= 4361
         (solve sample-data))))

(deftest solve2-test
  (is (= 467835
         (solve2 sample-data))))
