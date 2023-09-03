(ns aoc2015.11-test
  (:require
   [aoc2015.11 :refer [contains-3-sequential?
                       contains-pairs?
                       increment]]
   [clojure.test :refer [deftest is]]))

(deftest contains-3-sequential?-test
  (is (false? (contains-3-sequential? "aac")))
  (is (true? (contains-3-sequential? "abc"))))

(deftest contains-pairs?-test
  (is (false? (contains-pairs? "abcd")))
  (is (false? (contains-pairs? "aabc")))
  (is (false? (contains-pairs? "aaa")))
  (is (true? (contains-pairs? "aabb"))))

(deftest increment-test
  (is (= "b" (increment "a")))
  (is (= "c" (increment "b")))
  (is (= "a" (increment "z")))
  (is (= "ba" (increment "az")))
  (is (= "bb" (increment "ba"))))

