; 有理数
; n1/d1 + n2/d2 = (n1d2 + n2d1)/d1d2
; n1/d1 * n2/d2 = n1n2/d1d2
(define (make-rat n d)
    (let ((g (gcd n d)))
        (cons (/ n g)
            (/ d g))))
; 求最大公约数            
(define (gcd a b)
    (cond ((= b 0) a))
        (else (gcd b (modulo a b))))            
(define (numer x) 
    (car x))
(define (denom x) 
    (cdr x))
(define (print-rat x)
    (newline)
    (display (numer x))
    (display "/")
    (display (denom x)))    
(define (+rat x y) 
    (make-rat
        (+  (*  (numer x) 
                (denom y)) 
            (*  (numer y) 
                (denom x))) 
        (* (denom x) (denom y))))
(define (*rat x y) 
    (make-rat 
        (*  (numer x)
            (numer y))
        (*  (denom x)
            (denom y))))
(define a (make-rat 2 3))
(define b (make-rat 1 12))

(print-rat (+rat a b))            
(print-rat (*rat a b))            


