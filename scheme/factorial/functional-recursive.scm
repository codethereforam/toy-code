; 函数式递归实现阶乘
(define (factorial n)
    (if (>= n 1)
        (* n (factorial (- n 1)))
        1))
(factorial 5)