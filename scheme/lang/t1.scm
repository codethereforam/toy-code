
; 表达式
(+ 1 2)

; 复合表达式
(* (+ 1 2) 
    (/ 4 2))

; 函数
(define (f1 x)
    (+ x 1))

(define (multiply a b)
            (* a b))
; 平方函数
(define (square x)
            (multiply  x x))


(define (g x)
    (square (f1 x))
)


(g 2)

(define (judge x)
    (if (< x 0)
        (display "负数")
        (display "非负数"))        
)

(judge -1)
(judge 0)
(judge 2)

(define (j1 x)
            (if (< x 0)
            -99
            99))
(j1 -2)
(j1 3)

; 递归; sum(5) = 5 + 4 + 3 + 2 + 1
(define (sum x)
        (if (= x 1)
            1
            (+ x (sum (- x 1)))))
(sum 9)     

; 循环; sum(5) = 5 + 4 + 3 + 2 + 1

; lambda

; 无参函数
(define (f)
    2)

(f)

; 函数作为函数的参数; 
(define (multi-func multi)
    (define (f x)
        (* multi x))
    f)

; (define (double-func x)
;     ((multi-func 2) x))

(define a 2)

(display a)

(define double-func (multi-func 2))

(double-func 3)
    
(define triple-func (multi-func 3))

(triple-func 3)

(define (multi-func1 multi)
    (lambda (x)
       (* multi x)))

(define double-func1 (multi-func1 2))

(double-func1 3)
    
(define triple-func1 (multi-func1 3))

(triple-func1 3)

; 函数作为函数的返回值

; 高阶函数
