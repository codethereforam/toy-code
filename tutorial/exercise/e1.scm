
(define (square x)
            (*  x x))

(define (f1 x)
            (+ (square x) 1))

(= (f1 2) 5)

(define (double x)
            (* 2 x))    

(= (double 10) 20)

(define (g x)
            (double (f1 x)))

(g 2)            


(define (factorial x)
        (if (= x 1)
            1
            (* x (factorial (- x 1)))))

(factorial 5)

(define (sum2 x))
        (if (= x 1)
            (square 1)
            (+ (square x) (sum2 (- x 2))))

(sum2 3)