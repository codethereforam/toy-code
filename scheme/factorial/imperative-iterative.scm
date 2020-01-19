; 命令式迭代实现阶乘
(define (factorial n)
    (let ((i 1) (r 1))
         (define (loop)
            (if (<= i n)
                (begin
                    (set! r (* i r))
                    (set! i (1+ i))
                    (loop))
                r))
          (loop)))
(factorial 5)