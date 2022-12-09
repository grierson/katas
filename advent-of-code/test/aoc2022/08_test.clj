(ns aoc2022.08-test
  (:require [clojure.test :refer [deftest is testing are]]
            [aoc2022.08 :refer [sample
                                parse
                                surrounding
                                surrounding-trees
                                visible?
                                distance
                                solve
                                solve2]]))

(def sample-grid (parse sample))

(deftest surrounding-test
  (testing "surrounding locations move outwards"
    (is (= {:left [[2 1] [2 0]]
            :right [[2 3] [2 4]]
            :up [[1 2] [0 2]]
            :down [[3 2] [4 2]]}
           (surrounding 4 [2 2])))))

(deftest surrounding-trees-test
  (testing "surrounding locations"
    (is (= {:left '(2)
            :right '(5 1 2)
            :up '(0)
            :down '(5 3 5)}
           (surrounding-trees sample-grid [1 1])))
    (is (= {:left '(5 3 3)
            :right '(9)
            :up '(3 1 7)
            :down '(9)}
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

(deftest distance-test
  (is (= {:up 1
          :left 1
          :right 2
          :down 2}
         (distance 5 (surrounding-trees sample-grid [1 2]))))
  (is (= {:up 2
          :left 2
          :down 1
          :right 2}
         (distance 5 (surrounding-trees sample-grid [3 2])))))

(deftest solve-test
  (is (= 21
         (solve sample-grid))))

(deftest solve2-test
  (is (= 8
         (solve2 sample-grid))))

