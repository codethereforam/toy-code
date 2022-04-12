; 小于等于给定参数x所有奇数（x为奇数）/偶数（x为偶数）的平方和
; f(7) = 1^2 + 3^2 + 5^2 + 7^2
; f(8) = 2^2 + 4^2 + 6^2 + 8^2
(define (square x)
        (* x x))

(square 2)

(define (sum-two x)
        (cond ((= x 1) (square 1))
            ((= x 2) (square 2))
            (else   (+ (square x) (sum-two (- x 2))))))

(sum-two 5)
(sum-two 6)


(modulo 4 2) 
(modulo 3 2) 

(define (odd-or-even x)
        (if (= (modulo x 2) 0)
            2
            1))

(odd-or-even 3)
(odd-or-even 4)

(define (sum-odd x)
        (if (= x 1)
            (square 1)
            (+ (square x) (sum-odd (- x 2)))))

(sum-odd 5)

(define (sum-even x)
        (if (= x 2)
            (square 2)
            (+ (square x) (sum-even (- x 2)))))

(sum-even 6)

(define (square-plus x)
        (if (= (odd-or-even x) 1)
            (sum-odd x)
            (sum-even x)))

(square-plus 5)
(square-plus 6)