(define (abs x)
    (if (< x 0)
        (- x)
        x))
(display (abs 1))
(newline)
(display (abs -1))
(newline)
(display (abs 0))
(newline)
(define (abs-cond x)
    (cond ((< x 0) (- x))
        ((>= x 0) x)))
(display (abs-cond 1))
(newline)
(display (abs-cond -1))
(newline)
(display (abs-cond 0))
(newline)