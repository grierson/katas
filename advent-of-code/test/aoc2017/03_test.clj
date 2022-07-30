(ns aoc2017.03-test
  (:require
   [aoc2017.03 :refer [find-spiral find-axis steps->axis solve1]]
   [clojure.test :refer [deftest is testing]]))

(deftest find-spiral-test
  (is (= {:step 0 :spiral [1 1]}
         (find-spiral 1)))
  (is (= {:step 1 :spiral [2 9]}
         (find-spiral 2)))
  (is (= {:step 2 :spiral [10 25]}
         (find-spiral 10)))
  (is (= {:step 3 :spiral [26 49]}
         (find-spiral 26))))

(deftest find-axis-test
  (is (= [2 4 6 8]
         (find-axis {:step 1 :spiral [2 9]})))
  (is (= [11 15 19 23]
         (find-axis {:step 2 :spiral [10 25]})))
  (is (= [28 34 40 46]
         (find-axis {:step 3 :spiral [26 49]}))))

(deftest steps->axis-test
  (is (= 1
         (steps->axis [2 4 6 8] 3)))
  (is (= 2
         (steps->axis [11 15 19 23] 13)))
  (testing "Moves to closes axis"
    (is (= 1
           (steps->axis [28 34 40 46] 27)))
    (is (= 1
           (steps->axis [28 24 40 46] 29)))))

(deftest solve1-test
  (is (= 0
         (solve1 1)))
  (is (= 1
         (solve1 2)))
  (is (= 2
         (solve1 3)))
  (is (= 2
         (solve1 11)))
  (is (= 3
         (solve1 12)))
  (is (= 6
         (solve1 31))))
