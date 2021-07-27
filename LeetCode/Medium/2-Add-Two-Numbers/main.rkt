#lang racket
(require rackunit)

(struct list-node
  (val next) #:mutable #:transparent)

(define (list->number l)
  (define (foo state ll)
    (if (false? ll)
        state
        (foo (cons (list-node-val ll) state) (list-node-next ll))))
  (string->number (apply string-append (map number->string (foo (list) l)))))

(define (number->list n)
  (define (foo l)
    (if (empty? l)
        #f
        (list-node (string->number (string (first l))) (foo (rest l)))))
  (foo (reverse (string->list (number->string n)))))


(define (add-two-numbers l1 l2)
  (number->list (+ (list->number l1) (list->number l2))))

(check-equal?
 (add-two-numbers
  (list-node 2 (list-node 4 (list-node 3 #f)))
  (list-node 5 (list-node 6 (list-node 4 #f))))
 (list-node 7 (list-node 0 (list-node 8 #f))))
