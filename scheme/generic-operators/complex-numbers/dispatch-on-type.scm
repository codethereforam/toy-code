; use 'dispatch on type' method to realize generic operators for complex numbers
;   +c          -c          *c          /c 
;-----------------------------------------------
;   real-part   imag-part   magnitude   angle
;-----------------------------------------------
;                   |
;       rect        |           polar
;                   |

; support mechanism for manifest types
(define (attach-type type content)
    (cons type content))
(define (type datum)
    (car datum))
(define (content datum)
    (cdr datum))

; type predicates
(define (rect? z)
    (eq? (type z) 'rect))
(define (polar? z)
    (eq? (type z) 'polar))

; rectangular package
(define (make-rect x y)
    (attach-type 'rect (cons x y)))
(define (real-part-rect z)
    (cdr z))
(define (imag-part-rect z)
    (cdr z))
(define (magnitude-rect z)
    (sqrt (+ (square (car z)))
             (square (cdr z))))
(define (angle-rect z)
    (atan (cdr z) 
          (car z)))

; polar package
(define (make-polar r a)
    (attach-type 'polar (cons r a))
(define (real-part-polar z))
(define (imag-part-polar z))
(define (magnitude-polar z))
(define (angle-polar z))

; generic selectors for complex numbers
(define (real-part z)
    (cond ((rect? z) 
            (real-part-rect (content z)))
          ((polar? z) 
            (real-part-polar (content z)))))
(define (imag-part z))
(define (magnitude z))
(define (angle z))

; generic operators for complex numbers
(define (+c  z1 z2)
    (make-rect 
        (+ (real-part z1) (real-part z2))
        (+ (imag-part z1) (imag-part z2))))
(define (-c  z1 z2))
(define (*c  z1 z2))
(define (/c  z1 z2))