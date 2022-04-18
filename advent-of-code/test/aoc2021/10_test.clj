(ns aoc2021.10-test
  (:require [clojure.test :refer :all]
            [aoc2021.10 :refer :all]))

(deftest corrupt-test
  (is (nil? (corrupt "()")))
  (is (= \} (corrupt "(}")))
  (is (= \> (corrupt "{()()()>")))
  (is (= \} (corrupt "(((()))}")))
  (is (= \) (corrupt "<([]){()}[{}])"))))

(deftest step-corrupted-test
  (is (= \) (unreduced (step-corrupted? '() \)))))
  (is (= '(\() (unreduced (step-corrupted? '() \())))
  (is (= '() (unreduced (step-corrupted? '(\() \))))))


(deftest sample-test
  (is (= 26397
         (solve sample))))
