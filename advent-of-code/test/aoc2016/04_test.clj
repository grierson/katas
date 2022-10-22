(ns aoc2016.04-test
  (:require
   [aoc2016.04 :refer [get-hash
                       get-name
                       get-sector-id
                       order-name
                       parse-line
                       update-state]]
   [clojure.test :refer [are deftest]]))

(deftest get-name-test
  (are [actual expected]
       (= (get-name actual) expected)
    "aaaaa-bbb-z-y-x-123[abxyz]" "aaaaa-bbb-z-y-x"
    "a-b-c-d-e-f-g-h-987[abcde]" "a-b-c-d-e-f-g-h"
    "not-a-real-room-404[oarel]" "not-a-real-room"
    "totally-real-room-200[decoy]" "totally-real-room"))

(deftest order-name-test
  (are [actual expected]
       (= (order-name actual) expected)
    "aaaaabbbzyx" "abxyz"
    "abcdefgh" "abcde"
    "notarealroom" "oarel"
    "totallyrealroom" "loart"))

(deftest get-sector-id-test
  (are [actual expected]
       (= (get-sector-id actual) expected)
    "aaaaa-bbb-z-y-x-123[abxyz]" 123
    "a-b-c-d-e-f-g-h-987[abcde]" 987
    "not-a-real-room-404[oarel]" 404
    "totally-real-room-200[decoy]" 200))

(deftest get-hash-test
  (are [actual expected]
       (= (get-hash actual) expected)
    "aaaaa-bbb-z-y-x-123[abxyz]" "abxyz"
    "a-b-c-d-e-f-g-h-987[abcde]" "abcde"
    "not-a-real-room-404[oarel]" "oarel"
    "totally-real-room-200[decoy]" "decoy"))

(deftest update-state-test
  (are [actual expected]
       (= (update-state 0 (parse-line actual)) expected)
    "aaaaa-bbb-z-y-x-123[abxyz]" 123
    "a-b-c-d-e-f-g-h-987[abcde]" 987
    "not-a-real-room-404[oarel]" 404
    "totally-real-room-200[decoy]" 0))
