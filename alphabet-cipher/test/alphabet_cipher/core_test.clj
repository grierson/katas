(ns alphabet-cipher.core-test
  (:require [midje.sweet :refer [future-facts fact facts =>]]
            [alphabet-cipher.core :refer [encode]]))

(future-facts "can encode a message with a secret keyword"
       (fact "vigilance"
             (encode "vigilance" "meetmeontuesdayeveningatseven") => "hmkbxebpxpmyllyrxiiqtoltfgzzv"))


(facts "silly"
       (fact "nil"
             (encode nil nil) => "")
       (fact "empty"
             (encode "" "") => ""))

(facts "message with a keyword"
       (fact "a a = a"
             (encode "a" "a") => "a")
       (fact "a b = b"
             (encode "a" "b") => "b")
       (fact "a c = b"
             (encode "a" "c") => "c"))

(facts "message with b keyword"
       (fact "b" "a"
             (encode "b" "a") => "b"))
