; package of supporting mechanism for manifest types
(define (attach-type type content)
    (cons type content))
(define (type datum)
    (car datum))
(define (content datum)
    (cdr datum))