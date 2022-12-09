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
    (is (= {:left [[1 0]]
            :right [[1 2] [1 3] [1 4]]
            :top [[0 1]]
            :bottom [[2 1] [3 1] [4 1]]}
           (surrounding 4 [1 1])))))

(deftest surrounding-trees-test
  (testing "surrounding locations"
    (is (= {:left '(2)
            :right '(5 1 2)
            :top '(0)
            :bottom '(5 3 5)}
           (surrounding-trees sample-grid [1 1])))
    (is (= {:left '(3 3 5)
            :right '(9)
            :top '(7 1 3)
            :bottom '(9)}
           (surrounding-trees sample-grid [3 3])))))

(deftest visible-test
  (testing "examples"
    (are [expected location]
         (= expected (visible?
                      (get-in sample-grid location)
                      (surrounding-trees sample-grid location)))
      true [1 1]
      true [1 2]
      false [1 3]

      true [2 1]
      false [2 2]
      true [2 3]

      false [3 1]
      true [3 2]
      false [3 3])))
