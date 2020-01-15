; sum
(define (sum term a next b)
    (if (> a b)
        0
        (+  (term a)
            (sum term (next a) next b))))

(define (sum-int a b)
    (define (indentity x) x)
        (sum indentity a 1+ b))
(display (sum-int 3 6))
(newline)

(define (sum-sq a b)
    (define square (lambda (x) (* x x)))
        (sum square a 1+ b))
(display (sum-sq 3 6))
(newline)

(define (sum-pi a b)
        (sum (lambda(x) (/ 1 (* x (+ x 2)))) 
            a 
            (lambda(x) (+ x 4)) 
            b))
(display (sum-pi 1 11))