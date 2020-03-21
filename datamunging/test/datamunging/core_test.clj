(ns datamunging.core-test
  (:require [midje.sweet :refer :all]
            [datamunging.core :refer :all]))

(fact "parse-record to ints"
  (parse-record '("9", "86", "32")) => '(9, 86, 32))

(future-fact "parse-record to ints"
             (parse-record '("9", "86", "32*")) => '(9, 86, 32))

(fact "strip stars"
  (strip-star "32*") => "32")

(fact "mo end"
  (strip-star "32*") => "32")

(fact "difference"
  (difference {:min 1 :max 2}) => 1)