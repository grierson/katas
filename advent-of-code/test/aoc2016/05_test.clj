(ns aoc2016.05-test
  (:require
   [aoc2016.05 :refer [solve
                       append-valid-hash
                       valid?]]
   [clojure.test :refer [deftest is are]]))

(deftest valid?-test
  (is (false? (valid? "0000abc")))
  (is (true? (valid? "00000abc"))))

(deftest append-valid-hash-test
  (are [actual expected]
       (= (nth (first (append-valid-hash [] actual)) 5)
          expected)
    "abc3231929" \1
    "abc5017308" \8
    "abc5278568" \f))

(comment
  (deftest find-hash-test
    (is (= (solve "abc") "18f47a30"))))
