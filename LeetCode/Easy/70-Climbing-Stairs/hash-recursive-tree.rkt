#lang racket
(require rackunit)


(define (climb-stairs n)
  (define (foo tree i)
    (if (hash-has-key? tree n)
        tree
        (foo
         (hash-set
          tree
          i
          (+ (hash-ref tree (- i 1))
             (hash-ref tree (- i 2))))
         (+ i 1))))
  (hash-ref (foo (hash 1 1 2 2) 3) n))

(check-equal?
 (climb-stairs 2) 2)

(check-equal?
 (climb-stairs 3) 3)

; (time (climb-stairs 40))
