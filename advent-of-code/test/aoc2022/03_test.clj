(ns aoc2022.03-test
  (:require [clojure.test :refer [deftest is]]
            [aoc2022.03 :refer [sample duplicates solve]]))

(deftest duplicates-test
  (is (= #{\p}
         (duplicates "vJrwpWtwJgWrhcsFMMfFFhFp")))
  (is (= #{\L}
         (duplicates "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL")))
  (is (= #{\P}
         (duplicates "PmmdzqPrVvPwwTWBwg")))
  (is (= #{\v}
         (duplicates "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn")))
  (is (= #{\t}
         (duplicates "ttgJtRGJQctTZtZT")))
  (is (= #{\s}
         (duplicates "CrZsJsPPZsGzwwsLwLmpwMDw"))))

(deftest solve-test
  (is (= 157
         (solve sample))))
