#lang racket
(require rackunit)
(require racket/trace)

(define (foo cnt jewels xs)
  (cond 
    [(empty? xs) cnt]
    [(member (first xs) jewels) (foo (add1 cnt) jewels (rest xs))]
    [else (foo cnt jewels (rest xs))]))

(define (num-jewels-in-stones jewels stones)
  (foo 0 (string->list jewels) (string->list stones)))

(trace foo)

(check-equal? (num-jewels-in-stones "" "") 0)
(check-equal? (num-jewels-in-stones "a" "") 0)
(check-equal? (num-jewels-in-stones "" "a") 0)
(check-equal? (num-jewels-in-stones "a" "a") 1)
(check-equal? (num-jewels-in-stones "a" "b") 0)
(check-equal? (num-jewels-in-stones "a" "aa") 2)

; Examples
; (check-equal? (num-jewels-in-stones "aA" "aAAbbbb") 3)
; (check-equal? (num-jewels-in-stones "z" "ZZ") 0)
