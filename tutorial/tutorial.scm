; 函数作为另一个函数的参数和返回值
(define f1 
    (lambda (x) (+ x 1)))
(define (f2 x) 
    (+ x 1))

(define (compose f g)
    (lambda (x) 
        (f (g x))))
(define (f x) 
    (* x x))
(define (g x) 
    (+ x 1))
(define t (compose f g))
(t 2)