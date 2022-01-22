(ns aoc2016.03-test
  (:require [clojure.test :refer :all]
            [aoc2016.03 :refer [valid?]]))

(deftest invalid-test
  (is (false? (valid? [1 1 3])))
  (is (false? (valid? [5 10 25]))))

(deftest valid-test
  (is (true? (valid? [1 1 1]))))
