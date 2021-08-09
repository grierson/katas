#lang racket
(require data/queue rackunit)

(struct tree-node
  (val left right) #:mutable #:transparent)

(define (is-symmetric root)
  (define (foo n1 n2)
    (cond
      [(and (false? n1) (false? n2)) true]
      [(or (false? n1) (false? n2)) false]
      [else (and (= (tree-node-val n1) (tree-node-val n2))
                 (foo (tree-node-right n1) (tree-node-left n2))
                 (foo (tree-node-left n1) (tree-node-right n2)))]))
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
