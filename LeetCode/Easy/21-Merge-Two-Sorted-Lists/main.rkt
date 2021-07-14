#lang racket
(require rackunit)

(struct list-node
  (val next) #:mutable #:transparent)

(define (mkn val [next #f])
  (list-node val next))

;; -----

(define/match (merge-two-lists l1 l2)
  [(#f #f) #f]
  [(n #f) n]
  [(#f n) n]
  [((list-node l1v l1n) (list-node l2v l2n))
   (if (< l1v l2v)
       (list-node l1v (merge-two-lists l1n l2))
       (list-node l2v (merge-two-lists l1 l2n)))])


(check-equal?
 (merge-two-lists (mkn 1 (mkn 2 (mkn 4)))
                  (mkn 1 (mkn 3 (mkn 4))))
 (mkn 1 (mkn 1 (mkn 2 (mkn 3 (mkn 4 (mkn 4)))))))
