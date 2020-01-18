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