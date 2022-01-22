(ns aoc2020.12-test
  (:require [clojure.test :refer :all]
            [aoc2020.12 :refer [move
                                north
                                forward]]))

(deftest sample-test
  (testing "step 1"
    (is (= (move [:E [0 0]] ["F" 10]) [:E [10 0]])))
  (testing "step 2"
    (is (= (move [:E [10 0]] ["N" 3]) [:E [10 3]])))
  (testing "step 3"
    (is (= (move [:E [10 3]] ["F" 7]) [:E [17 3]])))
  (testing "step 4"
    (is (= (move [:E [17 3]] ["R" 90]) [:S [17 3]])))
  (testing "step 5"
    (is (= (move [:S [17 3]] ["F" 11]) [:S [17 -8]]))))


(deftest forward-test
  (are [actual expected]
    (= actual expected)
    (forward [:N [0 0]] 10) [:N [0 10]]
    (forward [:E [0 0]] 10) [:E [10 0]]
    (forward [:S [0 0]] 10) [:S [0 -10]]
    (forward [:W [0 0]] 10) [:W [-10 0]]))

(deftest north-test
  (is (= (north [:N [0 0]] 10)
         [:N [0 10]])))
