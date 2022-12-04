(ns aoc2022.04-test
  (:require [clojure.test :refer [deftest is]]
            [aoc2022.04 :refer [sample
                                parse 
                                fully-contains
                                solve]]))

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
