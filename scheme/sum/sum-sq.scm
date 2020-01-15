; sum-sq a^2...b^2, (sum-sq 3 6) = (+ 3^2 4^2 5^2 6^2) = (+ 9 16 25 36) = 86
(define square (lambda (x) (* x x)))
(define (sum-sq a b)
    (if (> a b)
        0
        (+  (square a)
            (sum-sq (1+ a) b))))
(display (sum-sq 3 6))