; sum a...b, (sum 3 6) = (+ 3 4 5 6) = 18
(define (sum-int a b)
    (if (> a b)
        0
        (+  a
            (sum-int (1+ a) b))))
(display (sum-int 3 6))