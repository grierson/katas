(ns aoc2016.05-test
  (:require
   [aoc2016.05 :refer [append-valid-hash
                       append-valid-hash2
                       password-complete?
                       update-password
                       solve
                       valid?]]
   [clojure.test :refer [are deftest is testing]]))

(deftest valid?-test
  (is (false? (valid? "0000abc")))
  (is (true? (valid? "00000abc"))))

(deftest append-valid-hash-test
  (are [actual expected]
       (= (append-valid-hash "" actual)
          expected)
    "abc3231929" "1"
    "abc5017308" "8"
    "abc5278568" "f"))

(deftest update-password-test
  (testing "do not replace non \\_ values"
    (is (= "1____" (update-password "_____" 0 \1))))
  (testing "do not replace non \\_ values"
    (is (= "1____" (update-password "1____" 0 \x)))))

(deftest password-complete-test
  (is (false? (password-complete? "12_")))
  (is (true? (password-complete? "123"))))

(deftest append-valid-hash2-test
  (are [state id expected]
       (= (append-valid-hash2 state id)
          expected)
    "________" "abc3231929" "_5______"
    "_5______" "abc5017308" "_5______"
    "_5______" "abc5357525" "_5__e___"))

(comment
  (deftest find-hash-test
    (is (= (solve "abc") "18f47a30"))))
