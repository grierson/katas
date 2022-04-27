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
              ""
              "fold along y=7"
              "fold along x=5"])

(deftest parse-test
  (is (= {:dots  #{[6 10]
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
                   [9, 0]}
          :folds [[:y 7]
                  [:x 5]]}
         (parse example))))



(deftest get-y-half-test
  (let [{:keys [dots]} (parse example)]
    (is (= #{[6 10]
             [0 14]
             [9 10]
             [4 11]
             [6 12]
             [0 13]
             [10 12]
             [1 10]
             [2 14]
             [8 10]}
           (:bottom (get-y-halfs 7 dots))))))



(deftest get-x-half-test
  (let [dots (apply-fold [:y 7] (:dots (parse example)))]
    (is (= #{[6 0]
             [9 0]
             [6 2]
             [10 2]
             [6 4]
             [8 4]
             [9 4]
             [10 4]}
           (:right (get-x-halfs 5 dots))))))

(deftest relocate-y-dot-test
  (testing "example with a bottom fold at line 7"
    (are [current-location new-location]
      (= new-location (relocate-y-dot 7 current-location))
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

(deftest relocate-x-dot-test
  (testing "example with a right fold at column 5"
    (are [current-location new-location]
      (= new-location (relocate-x-dot 5 current-location))
      [6 0] [4 0]
      [9 0] [1 0]
      [6 2] [4 2])))


(deftest apply-fold-test
  (testing "first example bottom fold"
    (let [dots (apply-fold [:y 7] (:dots (parse example)))]
      (is (= #{[6 4]
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
               [1 4]
               [2 0]
               [8 4]
               [9 0]}
             dots))))
  (testing "second example right fold"
    (let [dots (apply-fold [:x 5] (apply-fold [:y 7] (:dots (parse example))))]
      (is (= #{[0 0]
               [0 1]
               [0 2]
               [0 3]
               [0 4]
               [1 0]
               [1 4]
               [2 0]
               [2 4]
               [3 0]
               [3 4]
               [4 0]
               [4 1]
               [4 2]
               [4 3]
               [4 4]}
             dots)))))


(deftest execute-test
  (testing "execute example"
    (let [dots (execute (parse example))]
      (is (= 16 (count dots)))
      (is (= #{[0 0]
               [0 1]
               [0 2]
               [0 3]
               [0 4]
               [1 0]
               [1 4]
               [2 0]
               [2 4]
               [3 0]
               [3 4]
               [4 0]
               [4 1]
               [4 2]
               [4 3]
               [4 4]}
             dots)))))
