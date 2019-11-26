(ns anagram.core-test
  (:require [midje.sweet :refer :all]
            [clojure.java.io :as io]
            [anagram.core :refer :all]
            [clojure.string :as str]))

(defn anagram [words word]
  (if (and (some? word) (not-empty word))
    (->> words
         (filter #(= (count %) (count word)))
         (filter #(= (frequencies %) (frequencies word)))
         (remove #(= word %)))
    ""))

(comment
  (def dictionary (str/split-lines (slurp (io/resource "wordlist.txt"))))
  (anagram dictionary "documenting"))

(facts "degenerate test cases"
  (fact "should return empty string"
    (anagram nil "") => ""
    (anagram [] nil) => ""
    (anagram [] "") => ""))

(facts "word should not appear within result"
  (fact "art => [rat tar]"
    (anagram '("art" "rat" "tar") "art") => (just ["rat" "tar"] :in-any-order))
  (fact "rat => [art tar]"
    (anagram '("art" "rat" "tar") "rat") => (just ["art" "tar"] :in-any-order))
  (fact "tar => [art rat]"
    (anagram '("art" "rat" "tar") "tar") => (just ["rat" "art"] :in-any-order)))

(fact "given [art rat tar eat] then eat should not appear"
  (anagram '("art" "rat" "tar" "eat") "art") => (just ["rat" "tar"] :in-any-order))

