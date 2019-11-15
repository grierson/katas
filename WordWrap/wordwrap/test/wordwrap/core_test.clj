(ns wordwrap.core-test
  (:require [midje.sweet :refer :all]
            [wordwrap.core :refer :all]))

(facts "simple test"
  (wrap nil 0) => ""
  (wrap "" 1) => ""
  (wrap "a" 1) => "a"
  (wrap "aa" 1) => "a\na"
  (wrap "aaa" 1) => "a\na\na"
  (wrap "a a" 1) => "a\na"
  (wrap "a aa" 3) => "a\naa")
