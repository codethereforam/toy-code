(define (square x)
    (* x x))

(define (prime? x)
    (define (loop i)
        (cond ((> (square i) x) #t)
            ((= (modulo x i) 0) #f)
            (else (loop (+ i 1)))))
    (loop 2))