#lang racket
(require rackunit)

(struct tree-node
  (val left right) #:mutable #:transparent)

(define (merge-trees root1 root2)
  (cond
    [(false? root1) root2]
    [(false? root2) root1]
    [else
      (tree-node
       (+ (tree-node-val root1) (tree-node-val root2))
       (merge-trees (tree-node-left root1) (tree-node-left root2))
       (merge-trees (tree-node-right root1) (tree-node-right root2)))]))


(check-equal?
 (merge-trees #f #f)
 #f)

(check-equal?
 (merge-trees
  (tree-node 1 #f #f)
  #f)
 (tree-node 1 #f #f))

(check-equal?
 (merge-trees
  #f
  (tree-node 1 #f #f))
 (tree-node 1 #f #f))

(check-equal?
 (merge-trees
  (tree-node 1 #f #f)
  (tree-node 1 #f #f))
 (tree-node 2 #f #f))

(check-equal?
 (merge-trees
  (tree-node 1 (tree-node 2 #f #f) #f)
  (tree-node 1 (tree-node 2 #f #f) #f))
 (tree-node 2 (tree-node 4 #f #f) #f))
