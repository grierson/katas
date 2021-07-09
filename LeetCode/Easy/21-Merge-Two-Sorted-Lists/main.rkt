#lang racket
(require rackunit)

(struct list-node 
  (val next) #:mutable #:transparent)

(define (make-list-node [val 0])
  (list-node val #f))

;; -----

(define (merge-two-lists l1 l2)
  (make-list-node))


; Empty tests
(check-equal? (merge-two-lists (make-list-node) (make-list-node)) (make-list-node))
(check-equal? (merge-two-lists (make-list-node 1) (make-list-node)) (make-list-node 1))
(check-equal? (merge-two-lists (make-list-node) (make-list-node 1)) (make-list-node 1))