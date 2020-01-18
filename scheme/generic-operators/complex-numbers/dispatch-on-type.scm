; use 'dispatch on type' method to realize generic operators for complex numbers
;   +c          -c          *c          /c 
;-----------------------------------------------
;   real-part   imag-part   magnitude   angle
;-----------------------------------------------
;                   |
;       rect        |           polar
;                   |

(load "../../$.scm")

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
    (car z))
(define (imag-part-rect z)
    (cdr z))
(define (magnitude-rect z)
    (sqrt (+ (square (car z))
             (square (cdr z)))))
(define (angle-rect z)
    (atan (cdr z) 
          (car z)))

; polar package
(define (make-polar r a)
    (attach-type 'polar (cons r a)))
(define (real-part-polar z)
    (* (car z) (cos (cdr z))))
(define (imag-part-polar z)
    (* (car z) (sin (cdr z))))
(define (magnitude-polar z)
    (car z))
(define (angle-polar z)
    (cdr z))

; generic selectors for complex numbers
(define (real-part z)
    (cond ((rect? z) 
            (real-part-rect (content z)))
          ((polar? z) 
            (real-part-polar (content z)))))
(define (imag-part z)
    (cond ((rect? z) 
           (imag-part-rect (content z)))
          ((polar? z) 
           (imag-part-polar (content z)))))
(define (magnitude z)
    (cond ((rect? z) 
           (magnitude-rect (content z)))
          ((polar? z) 
           (magnitude-polar (content z)))))
(define (angle z)
     (cond ((rect? z) 
            (angle-rect (content z)))
           ((polar? z) 
            (angle-polar (content z)))))

; generic operators for complex numbers
(define (+c  z1 z2)
    (make-rect 
        (+ (real-part z1) (real-part z2))
        (+ (imag-part z1) (imag-part z2))))
(define (-c  z1 z2)
  (make-rect 
        (- (real-part z1) (real-part z2))
        (- (imag-part z1) (imag-part z2))))
(define (*c  z1 z2)
      (make-polar 
        (* (magnitude z1) (magnitude z2))
        (+ (angle z1) (angle z2))))
(define (/c  z1 z2)
    (make-polar 
        (/ (magnitude z1) (magnitude z2))
        (- (angle z1) (angle z2))))

(define c1 (make-rect 1 (sqrt 3))) ; (1, sqrt(3)) , (2, pai/3)  
(define c2 (make-rect 1 1)) ; (1 2) , (sqrt(2), pai/4)
(+c c1 c2) ; (rect 2 . 2.732050807568877)
(-c c1 c2) ; (rect 0 . 0.7320508075688772)
(*c c1 c2) ; (polar 2.82842712474619 . 1.832595714594046)
(/c c1 c2) ; (polar 1.4142135623730947 . 0.26179938779914935)