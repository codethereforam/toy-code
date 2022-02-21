(load "base-stream.scm")
(load "../$.scm")
  
(define (sinterval lo hi) 
  (if (> lo hi)  
  the-empty-stream 
  (cons-stream lo (sinterval (+ 1  lo) hi)))) 

(define s1 (sinterval 1 5))
(display-stream s1)

(define s2 (stream-map (lambda (x) (* x x)) s1))
(display-stream s2)

; 获取区间内的第二个素数
(stream-car ; 获取值
  (stream-cdr ; 再执行一次
    (stream-filter prime? (sinterval 10000 1000000)))) ; 执行一次