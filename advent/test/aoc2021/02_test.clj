(ns aoc2021.02-test
  (:require [clojure.test :refer :all]
            [aoc2021.02 :refer [make-sub
                                move apply-moves solve
                                move2 apply-moves2]]))

(deftest move-test
  (testing "forward"
    (is (= (make-sub {:x 1})
           (move (make-sub) ["forward" 1]))))

  (testing "up"
    (is (= (make-sub {:z -1})
           (move (make-sub) ["up" 1]))))

  (testing "down"
    (is (= (make-sub {:z 1})
           (move (make-sub) ["down" 1])))))

(deftest apply-moves-test
  (is (= (make-sub {:x 15 :z 10})
         (apply-moves (make-sub) [["forward" 5]
                                  ["down" 5]
                                  ["forward" 8]
                                  ["up" 3]
                                  ["down" 8]
                                  ["forward" 2]]))))
(deftest solve-test
  (is (= 150
         (solve {:x 15 :z 10}))))

(deftest move2-test
  (testing "forward"
    (is (= (make-sub {:x 1})
           (move2 (make-sub) ["forward" 1]))))

  (testing "up"
    (is (= (make-sub {:aim -1})
           (move2 (make-sub) ["up" 1]))))

  (testing "down"
    (is (= (make-sub {:aim 1})
           (move2 (make-sub) ["down" 1]))))

  (testing "examples steps"
    (testing "step one"
      (is (= (make-sub {:x 5})
             (move2 (make-sub) ["forward" 5]))))
    (testing "step two"
      (is (= (make-sub))))))

(deftest apply-moves2-test
  (is (= (make-sub {:x 15 :z 60 :aim 10})
         (apply-moves2 (make-sub) [["forward" 5]
                                   ["down" 5]
                                   ["forward" 8]
                                   ["up" 3]
                                   ["down" 8]
                                   ["forward" 2]]))))