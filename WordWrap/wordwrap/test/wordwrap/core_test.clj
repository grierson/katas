(ns wordwrap.core-test
  (:require [midje.sweet :refer :all]
            [wordwrap.core :refer :all]))

(fact "Degenerate test cases"
  (wrap nil 0) => ""
  (wrap "" 1) => "")

(fact "No breaks"
  (wrap "a" 1) => "a")

(fact "Remove space at start of string"
  (wrap " a" 1) => "a")

(fact "One break"
  (wrap "ab" 1) => "a\nb")

(fact "Two break"
  (wrap "abc" 1) => "a\nb\nc")

(fact "Ignore space and break"
  (wrap "a b" 1) => "a\nb")

(fact "Don't break mid word"
  (wrap "a bb" 3) => "a\nbb")
