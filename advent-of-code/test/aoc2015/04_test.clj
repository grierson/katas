(ns aoc2015.04-test
  (:require [clojure.test :refer [deftest is testing]])
  (:require [aoc2015.04 :refer [valid?]]))

(deftest valid?-test
  (testing "invalid"
    (is (false? (valid? ""))))
  (testing "valid"
    (is (true? (valid? "00000")))
    (is (true? (valid? "000001dbbfa3a5c83a2d506429c7b00e")))
    (is (true? (valid? "000006136ef2ff3b291c85725f17325c"))))
  (testing "valid2"
    (is (true? (valid? "000000")))))
