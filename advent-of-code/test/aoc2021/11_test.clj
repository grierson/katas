(ns aoc2021.11-test
  (:require [clojure.test :refer :all]
            [aoc2021.11 :refer :all]))

(deftest recharge-test
  (is (= {[0 0] 1 [0 1] 1
          [1 0] 1 [1 1] 1}
         (recharge (make-grid 2 2)))))

(deftest neighbours-test
  (testing "no negatives"
    (is (= [[0 1] [1 0] [1 1]]
           (neighbours [1 1] [0 0]))))
  (testing "no pass boundary"
    (is (= [[0 0] [0 1] [1 0]]
           (neighbours [1 1] [1 1])))))

(deftest charge-test
  (testing "Top left flashes"
    (let [grid (assoc
                 (make-grid 2 2)
                 [0 1] 1
                 [1 0] 1
                 [1 1] 1)]

      (is (= {[0 0] 0
              [0 1] 2
              [1 0] 1
              [1 1] 1}
             (charge grid [0 1]))))
    (testing "Don't charge flashed"
      (is (= {[0 0] 0
              [0 1] 0
              [1 0] 0
              [1 1] 0}
             (charge (make-grid 2 2) [0 1]))))))

(deftest flashers-test
  (testing "top left is a flasher"
    (let [grid (make-grid 2 2)
          grid (assoc grid [0 0] 10)]
      (is (= [[0 0]]
             (flashers grid))))
    (testing "all flashers"
      (let [grid (make-grid 2 2)
            grid (assoc grid [0 0] 10
                             [0 1] 10
                             [1 0] 10
                             [1 1] 10)]
        (is (= (set [[0 0] [0 1] [1 0] [1 1]])
               (set (flashers grid))))))))

(deftest flash-test
  (testing "top left flashes"
    (let [grid (assoc
                 (make-grid 2 2)
                 [0 0] 10
                 [0 1] 1
                 [1 0] 1
                 [1 1] 1)]
      (is (= {[0 0] 0
              [0 1] 2
              [1 0] 2
              [1 1] 2}
             (flash [1 1] grid [0 0]))))))

(deftest step-test
  (testing "smaller sample step"
    (let [init (str->grid ["11111"
                           "19991"
                           "19191"
                           "19991"
                           "11111"])
          step1 (str->grid ["34543"
                            "40004"
                            "50005"
                            "40004"
                            "34543"])
          step2 (str->grid ["45654"
                            "51115"
                            "61116"
                            "51115"
                            "45654"])]
      (is (= step1
             (step init [4 4])))
      (is (= step2
             (step step1 [4 4]))))))

(deftest bigger-step-test
  (testing "bigger sample step"
    (let [init (str->grid ["5483143223"
                           "2745854711"
                           "5264556173"
                           "6141336146"
                           "6357385478"
                           "4167524645"
                           "2176841721"
                           "6882881134"
                           "4846848554"
                           "5283751526"])
          step1 (str->grid ["6594254334"
                            "3856965822"
                            "6375667284"
                            "7252447257"
                            "7468496589"
                            "5278635756"
                            "3287952832"
                            "7993992245"
                            "5957959665"
                            "6394862637"])
          step2 (str->grid ["8807476555"
                            "5089087054"
                            "8597889608"
                            "8485769600"
                            "8700908800"
                            "6600088989"
                            "6800005943"
                            "0000007456"
                            "9000000876"
                            "8700006848"])]
      (is (= step1
             (step init [9 9])))
      (is (= step2
             (step step1 [9 9]))))))

(deftest sample-100-test
  (testing "sample to 100 steps"
    (let [init (str->grid ["5483143223"
                           "2745854711"
                           "5264556173"
                           "6141336146"
                           "6357385478"
                           "4167524645"
                           "2176841721"
                           "6882881134"
                           "4846848554"
                           "5283751526"])
          step100 (str->grid ["0397666866"
                              "0749766918"
                              "0053976933"
                              "0004297822"
                              "0004229892"
                              "0053222877"
                              "0532222966"
                              "9322228966"
                              "7922286866"
                              "6789998766"])]
      (is (= step100
             (run init [9 9] 100))))))
