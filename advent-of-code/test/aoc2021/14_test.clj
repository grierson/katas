(ns aoc2021.14-test
  (:require [clojure.test :refer :all])
  (:require [aoc2021.14 :refer :all]))

(deftest parse-test
  (is (= {:polymer "NNCB"
          :rules {"CH" "B"
                  "HH" "N"
                  "CB" "H"
                  "NH" "C"
                  "HB" "C"
                  "HC" "B"
                  "HN" "C"
                  "NN" "C"
                  "BH" "H"
                  "NC" "B"
                  "NB" "B"
                  "BN" "B"
                  "BB" "N"
                  "BC" "B"
                  "CC" "N"
                  "CN" "C"}}
         (parse example))))

(def example-rules (:rules (parse example)))

(deftest step-test
  (is (= "NCNBCHB"
         (step example-rules "NNCB")))
  (is (= "NBCCNBBBCBHCB"
         (step example-rules (step example-rules "NNCB")))))

(deftest steps-test
  (is (= "NBCCNBBBCBHCB"
         (steps example-rules 2 "NNCB"))))
