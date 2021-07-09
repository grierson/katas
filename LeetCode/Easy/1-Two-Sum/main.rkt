(define (make-dict coll)
  (define (foo state index xs)
    (if (empty? xs)
        state
        (foo (append state (list (list-ref coll index) index)) (add1 index) (rest xs))))
  (apply hash (foo '() 0 coll)))

(define (two-sum nums target)
  (define (foo d index)
    (let* ([complement (- target (list-ref nums index))]
           [x (hash-ref d complement null)])
      (if (and (not (null? x)) (not (= x index))) 
          (list index x)
          (foo d (add1 index)))))
  (foo (make-dict nums) 0))