#lang racket
(require rackunit)

(struct list-node
  (val next) #:mutable #:transparent)

(define (list->list-node coll)
  (if (empty? coll)
      #f
      (list-node (first coll) (list->list-node (rest coll)))))

(define (reverse-list head)
  (define (foo state node)
    (if (false? node)
        (list->list-node state)
        (foo (cons (list-node-val node) state) (list-node-next node))))
  (foo (list) head))

(check-equal?
 (reverse-list (list-node 1 (list-node 2 #f))) (list-node 2 (list-node 1 #f)))
