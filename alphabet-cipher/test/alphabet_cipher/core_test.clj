(ns alphabet-cipher.core-test
  (:require
   [clojure.test :refer [deftest testing is]]
   [alphabet-cipher.core :refer [encode repeat-keyword]]))

(deftest encode-test
  (testing "example case"
    (are [kw msg expected] (= expected (encode kw msg))
      "a" "a" "a"
      "a" "b" "b"
      "a" "c" "c"
      "a" "z" "z"
      "b" "a" "b"
      "b" "b" "c")))

(deftest example-case
  (testing "example case"
    (are [kw msg expected] (is (= expected (encode kw msg)))
      "s" "m" "e"
      "c" "e" "g"
      "o" "e" "e"
      "n" "t" "g"
      "e" "m" "q"
      "s" "e" "w"))
  (is (= "egsgqwtahuiljgs"
         (encode "sconessconessconessco" "meetmebythetree"))))

(deftest repeat-keyword-test
  (is (= "sconessconessco"
         (repeat-keyword "scones" "meetmebythetree"))))
