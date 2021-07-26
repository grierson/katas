#lang racket
(require rackunit)

(define (roman-to-int s)
  (define (foo sum roman)
    (match roman
      [(list) sum]
      [(list-rest #\I #\V xs) (foo (+ sum 4) xs)]
      [(list-rest #\I #\X xs) (foo (+ sum 9) xs)]
      [(list-rest #\X #\L xs) (foo (+ sum 40) xs)]
      [(list-rest #\X #\C xs) (foo (+ sum 90) xs)]
      [(list-rest #\C #\D xs) (foo (+ sum 400) xs)]
      [(list-rest #\C #\M xs) (foo (+ sum 900) xs)]
      [(list-rest #\I xs) (foo (+ sum 1) xs)]
      [(list-rest #\V xs) (foo (+ sum 5) xs)]
      [(list-rest #\X xs) (foo (+ sum 10) xs)]
      [(list-rest #\L xs) (foo (+ sum 50) xs)]
      [(list-rest #\C xs) (foo (+ sum 100) xs)]
      [(list-rest #\D xs) (foo (+ sum 500) xs)]
      [(list-rest #\M xs) (foo (+ sum 1000) xs)]))
  (foo 0 (string->list s)))


(define (test roman expected)
  (check-equal?
   (roman-to-int roman) expected roman))

;; 1 - 10
(test "I" 1)
(test "II" 2)
(test "III" 3)
(test "IV" 4)
(test "V" 5)
(test "VI" 6)
(test "VII" 7)
(test "VIII" 8)
(test "IX" 9)
(test "X" 10)

;; All prefix
(test "IV" 4)
(test "IX" 9)
(test "XL" 40)
(test "XC" 90)
(test "CD" 400)
(test "CM" 900)

;; Each symbol
(test "I" 1)
(test "V" 5)
(test "X" 10)
(test "L" 50)
(test "C" 100)
(test "D" 500)
(test "M" 1000)

;; Leetcode examples
(test "III" 3)
(test "IV" 4)
(test "IX" 9)
(test "LVIII" 58)
(test "MCMXCIV" 1994)
