(ns aoc2021.14-test
  (:require [clojure.test :refer :all])
  (:require [aoc2021.14 :refer :all]))



(deftest parse-test
  (is (= {:state "NNCB"
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
