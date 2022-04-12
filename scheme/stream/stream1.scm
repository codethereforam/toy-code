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

(define (stream-filter pred stream)
  (cond ((stream-null? stream) the-empty-stream)
    ((pred (stream-car stream)) 
      (cons-stream 
        (stream-car stream)
          (stream-filter pred
            (stream-cdr stream))))
    (else (stream-filter pred
            (stream-cdr stream)))))

(define (prime? n)
  (let loop ((d 2))
    (cond ((< n (* d d)) #t)
          ((zero? (modulo n d)) #f)
          (else (loop (+ d 1))))))

(stream-car 
  (stream-cdr 
    (stream-filter prime? 
      (sinterval 10000 1000000))))