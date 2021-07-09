(define (str->list s)
  (map string (string->list s)))

(define opposite (hash "(" ")" "[" "]" "{" "}"))

(define (get-first coll)
  (if (null? coll)
      null
      (first coll)))

(define (is-valid s)
  (define (foo state xs)
    (cond
      [(and (null? state) (null? xs)) #t]
      [(and (= 1 (length state)) (null? xs)) #f]
      [else (let ([x (get-first  xs)])
          (case x
            [("(" "[" "{") (foo (cons x state) (rest xs))]
            [(")" "]" "}") (if (string=? x (hash-ref opposite (get-first state) "")) 
                               (foo (rest state) (rest xs))
                               #f)]
            [else #f]))]))
  (foo '() (str->list s)))