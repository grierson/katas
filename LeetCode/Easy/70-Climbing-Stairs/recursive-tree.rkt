#lang racket
(require rackunit)

(define (climb-stairs n)
  (cond
    [(zero? n) 1]
    [(< n 0) 0]
    [else (+
           (climb-stairs (- n 2))
           (climb-stairs (- n 1)))]))

(check-equal?
 (climb-stairs 2) 2)

(check-equal?
 (climb-stairs 3) 3)
