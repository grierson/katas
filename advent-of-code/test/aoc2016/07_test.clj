(ns aoc2016.06-test
  (:require
   [aoc2016.06 :refer [most-common
                       least-common
                       get-letters
                       solve]]
   [clojure.test :refer [deftest is]]))

(def sample ["eedadn"
             "drvtee"
             "eandsr"
             "raavrd"
             "atevrs"
             "tsrnev"
             "sdttsa"
             "rasrtv"
             "nssdts"
             "ntnada"
             "svetve"
             "tesnvt"
             "vntsnd"
             "vrdear"
             "dvrsen"
             "enarar"])

(deftest get-letters-test
  (is (= "ederatsrnnstvvde" (get-letters sample 0)))
  (is (= "eraatsdastvenrvn" (get-letters sample 1)))
  (is (= "dvnaertssnestdra" (get-letters sample 2))))

(deftest most-common-test
  (is (= \e (most-common "ederatsrnnstvvde")))
  (is (= \a (most-common "eraatsdastvenrvn")))
  (is (= \s (most-common "dvnaertssnestdra"))))

(deftest least-common-test
  (is (= \a (least-common "ederatsrnnstvvde")))
  (is (= \d (least-common "eraatsdastvenrvn")))
  (is (= \v (least-common "dvnaertssnestdra"))))

(deftest solve-test
  (is (= "easter" (solve sample))))
