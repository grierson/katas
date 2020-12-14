(ns aoc2020.11-test
  (:require [aoc2020.11 :refer [apply-rules]]
            [midje.sweet :refer :all]
            [midje.experimental :refer [for-all gen-let]]
            [clojure.test.check.generators :as gen]))

(facts "apply-rules"
  (facts "open?"
    (fact "Given no neighbours then should take the seat"
      (apply-rules [] \L) => \#)

    (fact "Given one neighbour then seat should be left empty"
      (apply-rules [\#] \L) => \L)

    (for-all [neighbours (gen/vector (gen/elements [\# \L \.]))]
      (fact "Any amount of neighbours means the seat should be empty"
        (apply-rules (conj neighbours \#) \L) => \L)))

  (facts "crowded?"
    (fact "Given 3 neighbours then should not leave the seat"
      (apply-rules [\# \# \#] \#) => \#)
    (fact "Given 4 neighbours then should leave the seat"
      (apply-rules [\# \# \# \#] \#) => \L)))