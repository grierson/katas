(ns datamunging.core-test
  (:require [midje.sweet :refer :all]
            [datamunging.core :refer :all]))

(fact "strip stars"
  (strip-star "32*") => "32")
