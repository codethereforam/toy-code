; sum-pi 1/1*3 + 1/5*7 + 1/9*11
(define (sum-pi a b)
    (if (> a b)
        0
        (+  (/ 1 (* a (+ a 2)))
            (sum-pi (+ 4 a) b))))
(display (sum-pi 1 11))