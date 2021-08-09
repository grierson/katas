#lang racket
(require rackunit)

(struct tree-node
  (val left right) #:mutable #:transparent)

(define (is-symmetric root)
  (define (foo left right)
    (cond
      [(and (false? left) (false? right)) true]
      [(or (false? left) (false? right)) false]
      [else (and (= (tree-node-val left) (tree-node-val right))
                 (foo (tree-node-left left) (tree-node-right right))
                 (foo (tree-node-left right) (tree-node-right left)))]))
  (foo root root))

(check-equal?
 (is-symmetric (tree-node 1
                          (tree-node 2
                                     (tree-node 3 #f #f)
                                     (tree-node 4 #f #f))
                          (tree-node 2
                                     (tree-node 4 #f #f)
                                     (tree-node 3 #f #f))))
 #t)
