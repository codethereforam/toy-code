(define (my-delay exp)
  (lambda ()
    exp))
(define (my-force delayed-obj)
  (begin
    (display "be executed\n")
    (delayed-obj)
      ))

(define d1 (my-delay (+ 1 2)))
(my-force d1)
(my-force d1)

; -------------------------

(define d2 (delay (display "exec")))
(force d2)
(force d2)