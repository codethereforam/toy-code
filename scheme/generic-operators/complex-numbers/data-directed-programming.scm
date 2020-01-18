; use 'data-directed programming' method to realize generic operators for complex numbers

(load "../../$.scm")
(load "./base/type.scm")
(load "./base/polar-complex-number.scm")
(load "./base/rect-complex-number.scm")

(define (symbol-to-string-concat s1 s2)
    (string-append 
        (symbol->string s1)
        (symbol->string s2)))
(define ht (make-hashtable string-hash equal?))
(define (put type selector operation)
    (hashtable-set!
        ht 
        (symbol-to-string-concat type selector)  
        operation))               
(define (get type selector)
    (hashtable-ref ht (symbol-to-string-concat type selector) 'error))

; install the rectangular operations in the table
(put 'rect 'real-part real-part-rect)
(put 'rect 'imag-part imag-part-rect)
(put 'rect 'magnitude magnitude-rect)
(put 'rect 'angle angle-rect)

; install the polar operations in the table
(put 'polar 'real-part real-part-polar)
(put 'polar 'imag-part imag-part-polar)
(put 'polar 'magnitude magnitude-polar)
(put 'polar 'angle angle-polar)

(define (operate op obj)
    (let ((proc (get (type obj) op)))
         (if (not (null? proc))
             (proc (content obj))
             (error "undefined op"))))

; define the selectors using operate
(define (real-part z)
    (operate 'real-part z))
(define (imag-part z)
    (operate 'imag-part z))
(define (magnitude z)
    (operate 'magnitude z))
(define (angle z)
    (operate 'angle z))

(load "./base/generic-operators.scm")

(define c1 (make-rect 1 (sqrt 3))) ; (1, sqrt(3)) , (2, pai/3)  
(define c2 (make-rect 1 1)) ; (1 2) , (sqrt(2), pai/4)
(+c c1 c2) ; (rect 2 . 2.732050807568877)
(-c c1 c2) ; (rect 0 . 0.7320508075688772)
(*c c1 c2) ; (polar 2.82842712474619 . 1.832595714594046)
(/c c1 c2) ; (polar 1.4142135623730947 . 0.26179938779914935)