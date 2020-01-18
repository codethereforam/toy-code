(define l '(a b ))
(car l)
(cdr l)
(car (cdr l))
(cdr (cdr l))
(define c (cons 1 2))
c
(car c)
(cdr c)

(define a (lambda (x) (* x x)))
(define b (lambda (x) (* x x)))
(eq? a b)
(define c a)
(define d a)
(eq? c d)