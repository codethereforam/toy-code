; 函数作为另一个函数的参数和返回值
(define f1 
    (lambda (x) (+ x 1)))
(define (f2 x) 
    (+ x 1))

(define (compose f g)
    (lambda (x) 
        (f (g x))))
(define (f x) 
    (* x x))
(define (g x) 
    (+ x 1))
(define t (compose f g))
(t 2)

(define (factorial-iterator counter from to)
    (if (> from to)
        counter
        (factorial-iterator (* counter from) (+ from 1) to)))

(factorial-iterator 1 1 5)

(define (factorial number)
    (factorial-iterator 1 1 number))

(factorial 5)

(define consData (cons 2 5))
(car consData)
(cdr consData)

(cons 1 (list 2 3))

(define listData (list 1 2 3))
listData
; 1
(car listData)
; (2 3)
(cdr listData)
; (1 2 3)
(cons (car listData)
            (cdr listData))
(car (cdr listData))
; 3
(car (cdr (cdr listData)))
; ()
(cdr (cdr (cdr listData)))
(eq? (cdr (cdr (cdr listData))) '())
(null? (cdr (cdr (cdr listData))))

; (map (lambda (x) (+ x 1)) (1 2 3))

(define (square x) 
    (* x x))

; (map procedure mylist)
(define (map procedure mylist)
    (if (null? mylist)
        '()
        (cons (procedure (car mylist))
            (map procedure (cdr mylist)))))
(map square (list 1 2 3))

; filter