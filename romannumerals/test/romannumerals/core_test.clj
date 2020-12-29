(ns romannumerals.core-test
  (:require [midje.sweet :refer :all]
            [romannumerals.core :refer :all]))

(tabular
  (fact "Fake it til you make it"
    (number->roman2 ?num) => ?expected)
  ?num ?expected
  0 ""
  1 "I"
  2 "II"
  3 "III"
  4 "IV"
  5 "V"
  6 "VI"
  7 "VII"
  8 "VIII"
  9 "IX"
  10 "X"
  11 "XI"
  12 "XII"
  13 "XIII"
  14 "XIV"
  15 "XV"
  16 "XVI"
  17 "XVII"
  18 "XVIII"
  19 "XIX"
  20 "XX")

(tabular
  (fact "Specific"
    (number->roman2 ?num) => ?expected)
  ?num ?expected
  1 "I"
  4 "IV"
  5 "V"
  10 "X"
  40 "XL"
  50 "L"
  90 "XC"
  100 "C"
  500 "D"
  1000 "M"
  3000 "MMM")
