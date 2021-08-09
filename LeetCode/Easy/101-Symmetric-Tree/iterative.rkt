#lang racket
(require data/queue rackunit)

(struct tree-node
  (val left right) #:mutable #:transparent)


(define (is-symmetric root)
  (define (foo q)
    (if (queue-empty? q)
        #t
        (let* ([left (dequeue! q)]
               [right (dequeue! q)])
          (cond
            [(and (false? left) (false? right)) (foo q)]
            [(or (false? left) (false? right)) false]
            [(not (= (tree-node-val left) (tree-node-val right))) false]
            [else (enqueue! q (tree-node-left left))
                  (enqueue! q (tree-node-right right))
                  (enqueue! q (tree-node-right left))
                  (enqueue! q (tree-node-left right))
                  (foo q)]))))
  (let ([q (make-queue)])
    (enqueue! q root)
    (enqueue! q root)
    (foo q)))

(check-equal?
 (is-symmetric (tree-node 1
                          (tree-node 2
                                     (tree-node 3 #f #f)
                                     (tree-node 4 #f #f))
                          (tree-node 2
                                     (tree-node 4 #f #f)
                                     (tree-node 3 #f #f))))
 #t)
