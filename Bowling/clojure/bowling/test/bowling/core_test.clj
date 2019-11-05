(ns bowling.core-test
  (:require [clojure.test :refer :all]
            [bowling.core :refer :all]))

(deftest bowling
  (testing "original test cases"
    (are [line total] (= (score line) total)
      "X X X X X X X X X XXX" 300
      "9- 9- 9- 9- 9- 9- 9- 9- 9- 9-" 90
      "5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5" 150))

  (testing "only open scores"
    (is (= 99 (score "91 91 93 93 92 92 91 21 91 91"))))

  (testing "open and misses"
    (is (= 93 (score "91 9- 9- 9- 9- 9- 92 9- 9- 9-"))))

  (testing "mixture of strikes and opens"
    (is (= 98 (score "9- X 8- 9- 9- 9- 9- 9- 9- 9-")))
    (is (= 104 (score "9- X 8- 9- 9- 9- 9- 9- 9- X23")))
    (is (= 28 (score "-- X 81 -- -- -- -- -- -- --")))
    (is (= 27 (score "-- X 8- 1- -- -- -- -- -- --"))))

  (testing "mixture of spares and opens"
    (is (= 82 (score "9- 8/ -- 9- 9- 9- 9- 9- 9- 9-")))
    (is (= 84 (score "9- 8/ -- 9- 9- 9- 9- 9- 9- 9/1")))
    (is (= 12 (score "-- 8/ 1- -- -- -- -- -- -- --")))
    (is (= 11 (score "-- 8/ -1 -- -- -- -- -- -- --")))))