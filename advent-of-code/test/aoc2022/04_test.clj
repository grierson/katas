(ns aoc2022.04-test
  (:require [clojure.test :refer [deftest is]]
            [aoc2022.04 :refer [sample
                                parse
                                fully-contains
                                solve
                                any-overlap
                                solve2]]))

(deftest parse-assignment-test
  (is (= [#{2 3 4} #{6 7 8}]
         (parse "2-4,6-8")))
  (is (= [#{2 3} #{4 5}]
         (parse "2-3,4-5"))))

(deftest fully-contains-test
  (is (true? (fully-contains #{2 3 4 5 6 7 8}
                             #{3 4 5 6 7}))))

(deftest solve-test
  (is (= 2
         (solve sample))))

(deftest any-overlap-test
  (is (false? (any-overlap #{2 3 4} #{6 7 8})))
  (is (true? (any-overlap #{5 6 7} #{7 8 9}))))

(deftest solve2-test
  (is (= 4
         (solve2 sample))))
