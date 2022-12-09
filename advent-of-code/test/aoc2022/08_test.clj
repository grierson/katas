(ns aoc2022.08-test
  (:require [clojure.test :refer [deftest is testing are]]
            [aoc2022.08 :refer [sample
                                parse
                                surrounding
                                surrounding-trees
                                visible?]]))

(def sample-grid (parse sample))

(deftest surrounding-test
  (testing "surrounding locations"
    (is (= [[1 0] [1 2] [0 1] [2 1]]
           (surrounding 2 [1 1])))))

(deftest surrounding-trees-test
  (testing "surrounding locations"
    (is (= [2 5 1 2 0 5 3 5]
           (surrounding-trees sample-grid [1 1])))
    (is (= [3 3 5 9 7 1 3 9]
           (surrounding-trees sample-grid [3 3])))))

(deftest visible-test
  (testing "is tree visible edge cases"
    (is (true? (visible? 9 [0 0 0 5])))
    (is (false? (visible? 1 [9 9 9 9]))))
  (testing "examples"
    (are [expected location]
         (= expected (visible?
                      (get-in sample-grid location)
                      (surrounding-trees sample-grid location)))
      true [1 1]
      true [1 2]
      false [1 3]
      true [2 1])))
