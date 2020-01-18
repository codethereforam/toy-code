; use 'dispatch on type' method to realize generic operators for complex numbers
;   +c          -c          *c          /c 
;-----------------------------------------------
;   real-part   imag-part   magnitude   angle
;-----------------------------------------------
;                   |
;       rect        |           polar
;                   |

(load "../../$.scm")
(load "./base/type.scm")
(load "./base/polar-complex-number.scm")
(load "./base/rect-complex-number.scm")

; type predicates
(define (rect? z)
    (eq? (type z) 'rect))
(define (polar? z)
    (eq? (type z) 'polar))

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

(load "./base/generic-operators.scm")

(define c1 (make-rect 1 (sqrt 3))) ; (1, sqrt(3)) , (2, pai/3)  
(define c2 (make-rect 1 1)) ; (1 2) , (sqrt(2), pai/4)
(+c c1 c2) ; (rect 2 . 2.732050807568877)
(-c c1 c2) ; (rect 0 . 0.7320508075688772)
(*c c1 c2) ; (polar 2.82842712474619 . 1.832595714594046)
(/c c1 c2) ; (polar 1.4142135623730947 . 0.26179938779914935)