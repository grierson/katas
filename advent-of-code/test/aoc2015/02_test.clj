(ns aoc2015.02-test
  (:require [clojure.test :refer [deftest is]]
            [aoc2015.02 :refer [parse-measurement paper ribbon]]))

(deftest parse-measurement-test
  (is (= (parse-measurement "1x2x3")
         '(1 2 3))))

(deftest paper-test
  (is (= (paper '(2 3 4))
         58))
  (is (= (paper '(1 1 10))
         43)))

(deftest ribbon-test
  (is (= (ribbon '(2 3 4))
         34))
  (is (= (ribbon '(1 1 10))
         14)))
