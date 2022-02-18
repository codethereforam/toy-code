(define the-empty-stream '())

(define-syntax define-macro
  (lambda (x)
 (syntax-case x ()
   ((define-macro (name . params) body1 body2 ...)
    #'(define-macro name (lambda params body1 body2 ...)))
   ((define-macro name expander)
    #'(define-syntax name
     (lambda (x)
    (syntax-case x ()
      [(name . args) ;(_ . args) more hygiene!!
    (datum->syntax #'name (apply expander (syntax->datum #'args)))])))))))


(define-macro cons-stream
  (lambda (car cdr)
 `(cons ,car (delay ,cdr))))

(define empty-stream? null?)

(define stream-null? empty-stream?)

(define (stream-cdr stream)
  (force (cdr stream)))

(define (stream-car stream)
  (car stream))

(define (stream-for-each proc s)
  (if (stream-null? s)
   'done
   (begin (proc (stream-car s))
    (stream-for-each proc (stream-cdr s)))))

(define (display-line x)
  (newline)
  (display x))

(define (display-stream s)
  (stream-for-each display-line s))

  
(define (stream-map proc . streams)
  (if (stream-null? (car streams))
      the-empty-stream
      (cons-stream
       (apply proc (map stream-car streams))
       (apply stream-map
              (cons proc (map stream-cdr streams))))))


; -------------------------
  
(define (sinterval lo hi) 
  (if (> lo hi)  
  the-empty-stream 
  (cons-stream lo (sinterval (+ 1  lo) hi)))) 

(define s1 (sinterval 1 5))
(display-stream s1)

(define s2 (stream-map (lambda (x) (* x x)) s1))
(display-stream s2)