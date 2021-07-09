(define (get-x coll idx)
  (let ([x (list-ref coll idx)])
    (list-ref coll x)))

(define (build-array nums)
  (define (*build-array* idx state)
    (if (< idx 0)
        state
        (*build-array* (- idx 1) (cons (get-x nums idx) state))))
  (*build-array* (- (length nums) 1) '()))