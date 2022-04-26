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


(deftest relocate-dot-test
  (is (= [6 4]
         (relocate-y-dot 7 [6 10])))
  (is (= [0 14]
         (relocate-y-dot 7 [0 0])))
  (is (= [9 4]
         (relocate-y-dot 7 [9 10]))))
