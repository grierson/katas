(ns wordwrap.core-test
  (:require [midje.sweet :refer :all]
            [wordwrap.core :refer :all]))

(facts "silly"
  (fact "nil string"
    (wrap nil 1) => "")
  (fact "empty string"
    (wrap "" 1) => "")
  (fact "empty string with two spaces"
    (wrap "  " 1) => "")
  (fact "zero length"
    (wrap "abc" 0) => ""))

(facts "simple"
  (fact "single letter"
    (wrap "a" 10) => "a")
  (fact "space at start of string"
    (wrap " a" 10) => "a")
  (fact "single new line"
    (wrap "ab" 1) => "a\nb")
  (fact "double new line"
    (wrap "abc" 1) => "a\nb\nc")
  (fact "line with a space"
    (wrap "a b" 1) => "a\nb")
  (fact "break mid word"
    (wrap "a bc" 3) => "a\nbc"))

(facts "specific"
  (wrap "Lorem ipsum dolor sit amet, consectetur adipiscing elit." 11) => "Lorem ipsum\ndolor sit\namet,\nconsectetur\nadipiscing\nelit.")