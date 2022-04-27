(ns aoc2021.13-test
  (:require [clojure.test :refer :all])
  (:require [aoc2021.13 :refer :all]))


(def example ["6,10"
              "0,14"
              "9,10"
              "0,3"
              "10,4"
              "4,11"
              "6,0"
              "6,12"
              "4,1"
              "0,13"
              "10,12"
              "3,4"
              "3,0"
              "8,4"
              "1,10"
              "2,14"
              "8,10"
              "9,0"

              "fold along y=7"
              "fold along x=5"])

(deftest parse-test
  (is (= {:dots  [[6 10]
                  [0, 14]
                  [9, 10]
                  [0, 3]
                  [10, 4]
                  [4, 11]
                  [6, 0]
                  [6, 12]
                  [4, 1]
                  [0, 13]
                  [10, 12]
                  [3, 4]
                  [3, 0]
                  [8, 4]
                  [1, 10]
                  [2, 14]
                  [8, 10]
                  [9, 0]]
          :folds [[:y 7]
                  [:x 5]]}
         (parse example))))



(deftest get-bottom-half-test
  (let [{:keys [dots]} (parse example)]
    (is (= [[6 10]
            [0, 14]
            [9, 10]
            [4, 11]
            [6, 12]
            [0, 13]
            [10, 12]
            [1, 10]
            [2, 14]
            [8, 10]]
           (get-bottom-half dots 7)))))


(deftest relocate-y-dot-test
  (testing "example with a bottom fold at line 7"
    (are [current y]
      (= y (relocate-y-dot 7 current))
      [6 10] [6 4]
      [0 14] [0 0]
      [9 10] [9 4]
      [4 11] [4 3]
      [6 12] [6 2]
      [0 13] [0 1]
      [10 12] [10 2]
      [1 10] [1 4]
      [2 14] [2 0]
      [8 10] [8 4])))


(deftest apply-fold-test
  (is (= {:dots  [[6 4]
                  [0 0]
                  [9 4]
                  [0 3]
                  [10 4]
                  [4 3]
                  [6 0]
                  [6 2]
                  [4 1]
                  [0 1]
                  [10 2]
                  [3 4]
                  [3 0]
                  [8 4]
                  [1 4]
                  [2 0]
                  [8 4]
                  [9 0]]
          :folds [[:x 5]]}
         (apply-fold (parse example)))))
