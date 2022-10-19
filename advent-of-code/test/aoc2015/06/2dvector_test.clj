(ns aoc2015.06.2dvector-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [aoc2015.06.2dvector :refer [update-state turn-on]]))

(deftest turn-on-test
  (testing "Creates key"
    (is (= (update-state [[false false]
                          [false false]] {:action turn-on
                                          :coords [[0 0] [0 0]]})
           [[true false]
            [false false]]))))
