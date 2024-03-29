; 高阶函数 - 使用导数定义求导数值

(define dx 0.000001)
(define (d f) 
    (lambda (x) 
        (/  (-  (f (+ x dx))
                (f x))
            dx)))
(define (f x)
    (+  (* x x) x 1))
(define f1 (d f))
(f1 3)
(define f2 (d f1))
(f2 1)             