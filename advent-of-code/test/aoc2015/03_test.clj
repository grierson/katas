(ns aoc2015.03-test
  (:require
   [aoc2015.03 :refer [move update-log track]]
   [clojure.test :refer [deftest is testing]]))

(deftest move-test
  (is (= (move [0 0] \^) [0 1]))
  (is (= (move [0 0] \v) [0 -1]))
  (is (= (move [0 0] \<) [-1 0]))
  (is (= (move [0 0] \>) [1 0])))

(deftest update-log-test
  (testing "add new location"
    (is (= (update-log {} [0 0]) {[0 0] 1})))
  (testing "update existing location"
    (is (= (update-log {[0 0] 1} [0 0]) {[0 0] 2}))))

(deftest track-test
  (testing ">"
    (is (= (track ">")
           {[0 0] 1
            [0 1] 1})))
  (testing "^>v<"
    (is (= (track "^>v<")
           {[0 0] 2
            [1 0] 1
            [1 1] 1
            [0 1] 1})))
  (testing "^v^v^v^v^v"
    (is (= (track "^v^v^v^v^v")
           {[0 0] 6
            [0 1] 5}))))
