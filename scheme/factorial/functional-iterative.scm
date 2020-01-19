; 函数式迭代实现阶乘
(define (recursion n)
    (define (loop i r)
        (if (<= i n)
            (loop (1+ i) (* r i))
            r))
    (loop 1 1)
)
(recursion 5)