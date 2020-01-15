(define (cons a b)
    (lambda (pick) 
        (cond ((= pick 1) a)
            ((= pick 2) b))))
(define (car x) (x 1))
(define (cdr x) (x 2))
(display (car (cons 3 5)))
(newline)
(display (cdr (cons 3 5)))