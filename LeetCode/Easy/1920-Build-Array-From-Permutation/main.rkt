#lang racket
(require rackunit)

(define (get-x coll idx)
  (let ([x (list-ref coll idx)])
    (list-ref coll x)))

(define (build-array nums)
  (define (*build-array* idx state)
    (if (< idx 0)
        state
        (*build-array* (- idx 1) (cons (get-x nums idx) state))))
  (*build-array* (- (length nums) 1) '()))

(check-equal?
 (build-array (list 0 2 1 5 3 4)) (list 0 1 2 4 5 3))

(check-equal?
 (build-array (list 5 0 1 2 3 4)) (list 4 5 0 1 2 3))
