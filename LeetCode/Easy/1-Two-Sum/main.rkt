#lang racket
(require rackunit)

(define (make-dict coll)
  (define (foo state index xs)
    (if (empty? xs)
        state
        (foo (append state (list (list-ref coll index) index)) (add1 index) (rest xs))))
  (apply hash (foo '() 0 coll)))

(define (two-sum nums target)
  (let* ([vnums (list->vector nums)]
         [lvnums (sub1 (vector-length vnums))])
    (define (foo d index)
      (let* ([complement (- target (vector-ref vnums index))]
             [x (hash-ref d complement null)])
        (cond
          [(and (not (null? x)) (not (= x index))) (list index x)]
          [(= index lvnums) null]
          [else (foo d (add1 index))])))
    (foo (make-dict nums) 0)))

(check-equal?
 (two-sum (list 2 7 11 15) 9) (list 0 1))

(check-equal?
 (two-sum (list 3 2 4) 6) (list 1 2))

(check-equal?
 (two-sum (list 3 3) 6) (list 0 1))
