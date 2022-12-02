(ns aoc2016.02-test
  (:require
   [aoc2016.02 :refer [find-number
                       solve
                       get-coord
                       get-number
                       valid-move?
                       apply-moves]]
   [clojure.test :refer [are deftest is testing]]))

(deftest get-number-test
  (are [actual expected] (= actual expected)
    (get-number 5 "U") 2
    (get-number 5 "D") 8
    (get-number 5 "L") 4
    (get-number 5 "R") 6
    (get-number 5 "ULL") 1
    (get-number 1 "RRDDD") 9
    (get-number 9 "LURDL") 8
    (get-number 8 "UUUD") 5))

(deftest solve-test
  (is (= (solve ["ULL" "RRDDD" "LURDL" "UUUD"]) "1985")))

(deftest get-location-test
  (are [actual expected] (= (find-number actual) expected)
    [0 0] nil
    [2 0] 5
    [2 2] 7
    [0 2] 1))

(deftest get-coord-test
  (testing "get next coord"
    (is (= [1 0] (get-coord [2 0] \U)))
    (is (= [3 0] (get-coord [2 0] \D)))
    (is (= [2 -1] (get-coord [2 0] \L)))
    (is (= [2 1] (get-coord [2 0] \R)))))

(deftest get-next-coord-test
  (is (= nil (valid-move? [2 0] \L)))
  (is (= [2 1] (valid-move? [2 0] \R))))

(deftest apply-moves-test
  (is (= (apply-moves [2 0] "ULL") [2 0]))
  (is (= (apply-moves [2 0] "RRDDD") [4 2]))
  (is (= (apply-moves [4 2] "LURDL") [3 2]))
  (is (= (apply-moves [3 2] "UUUUD") [1 2])))
