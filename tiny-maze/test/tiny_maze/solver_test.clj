(ns tiny-maze.solver-test
  (:require [clojure.test :refer :all]
            [tiny-maze.solver :refer :all]))

(deftest test-solve-maze
  (testing "can find way to exit with 3x3 maze"
    (let [maze [[:S 0 1]
                [1 0 1]
                [1 0 :E]]
          sol [[:x :x 1]
               [1 :x 1]
               [1 :x :x]]]
      (is (= sol (solve-maze maze)))))

  (testing "can find way to exit with 4x4 maze"
    (let [maze [[:S 0 0 1]
                [1 1 0 0]
                [1 0 0 1]
                [1 1 0 :E]]
          sol [[:x :x :x 1]
               [1 1 :x 0]
               [1 0 :x 1]
               [1 1 :x :x]]]
      (is (= sol (solve-maze maze))))))


(def sample-maze [[:S 0 1]
                  [1 0 1]
                  [1 0 :E]])

(deftest test-free-space
  (testing "can find start space"
    (is (= :S (get-position sample-maze [0 0]))))

  (testing "can find end space"
    (is (= :E (get-position sample-maze [2 2]))))

  (testing "can find free space"
    (is (= 0 (get-position sample-maze [1 1]))))

  (testing "can find wall space"
    (is (= 1 (get-position sample-maze [0 2]))))

  (testing "can't find out of index"
    (is (= nil (get-position [] [0 0])))))
