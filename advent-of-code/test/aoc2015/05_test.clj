(ns aoc2015.05-test
  (:require [clojure.test :refer :all])
  (:require [aoc2015.05 :refer :all]))

(deftest contains-3-vowels?-test
  (testing "valid"
    (testing "aei"
      (is (true? (three-vowels? "aei"))))
    (testing "xazegov"
      (is (true? (three-vowels? "xazegov"))))
    (testing "aeiouaeiouaeiou"
      (is (true? (three-vowels? "aeiouaeiouaeiou")))))
  (testing "invalid"
    (testing "a"
      (is (false? (three-vowels? "a"))))
    (testing "aa"
      (is (false? (three-vowels? "aa"))))
    (testing "aab"
      (is (false? (three-vowels? "aab"))))))

(deftest contains-pair?-test
  (testing "silly cases"
    (is (false? (contains-pair? nil)))
    (is (false? (contains-pair? "")))
    (is (false? (contains-pair? "a"))))
  (testing "xx is valid"
    (is (true? (contains-pair? "xx"))))
  (testing "abcdde is valid (dd)"
    (is (true? (contains-pair? "abcdde"))))
  (testing "ac invalid"
    (is (false? (contains-pair? "ac"))))
  (testing "aabbccdd (aa, bb, cc, or dd) is valid"
    (is (true? (contains-pair? "aabbccdd"))))
  (testing "aba is not valid as a's are not next to each other"
    (is (false? (contains-pair? "aba")))))

(deftest contains-no-bad-pairs?-test
  (testing "finds bad pairs"
    (testing "ab"
      (is (false? (contains-no-bad-pairs? "ab"))))
    (testing "cd"
      (is (false? (contains-no-bad-pairs? "cd"))))
    (testing "pq"
      (is (false? (contains-no-bad-pairs? "pq"))))
    (testing "xy"
      (is (false? (contains-no-bad-pairs? "xy")))))
  (testing "no bad pairs"
    (is (true? (contains-no-bad-pairs? "aa")))))


(deftest nice?-test
  (testing "nice"
    (is (true? (nice? "ugknbfddgicrmopn")))
    (is (true? (nice? "aaa"))))
  (testing "naughty"
    (is (false? (nice? "jchzalrnumimnmhp")))
    (is (false? (nice? "haegwjzuvuyypxyu")))
    (is (false? (nice? "dvszwmarrgswjxmb")))))

(deftest seperated-pair?-test
  (testing "invalid seperated pair"
    (is (false? (seperated-pair? nil)))
    (is (false? (seperated-pair? "")))
    (is (false? (seperated-pair? "a")))
    (is (false? (seperated-pair? "aa")))
    (is (false? (seperated-pair? "aab"))))
  (testing "seperated pair"
    (is (true? (seperated-pair? "aba"))))
  (testing "examples"
    (is (true? (seperated-pair? "qjhvhtzxzqqjkmpb")))
    (is (true? (seperated-pair? "xxyxx")))
    (is (true? (seperated-pair? "ieodomkazucvgmuy")))))


(deftest two-pairs?-test
  (testing "invalid"
    (is (false? (two-pairs? nil)))
    (is (false? (two-pairs? "")))
    (is (false? (two-pairs? "a")))
    (is (false? (two-pairs? "ab")))
    (is (false? (two-pairs? "aba")))
    (is (false? (two-pairs? "aaa")) "second a duplicated across both pairs")
    (is (true? (two-pairs? "abab")))))
