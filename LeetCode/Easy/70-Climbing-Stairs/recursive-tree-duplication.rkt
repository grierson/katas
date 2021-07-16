#lang racket
(require rackunit)

(define/match (climb-stairs n)
    [(1) 1]
    [(2) 2]
    [(n) (+
           (climb-stairs (- n 2))
           (climb-stairs (- n 1)))])

(check-equal?
 (climb-stairs 2) 2)

(check-equal?
 (climb-stairs 3) 3)
