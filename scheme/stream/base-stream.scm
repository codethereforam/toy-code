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

(define (stream-filter pred stream)
    (cond ((empty-stream? stream) the-empty-stream) ; 空的stream 返回空stream
        ((pred (stream-car stream)) ; 如果car值符合条件
                (cons-stream (stream-car stream)
                    (stream-filter pred (stream-cdr stream)))) ; car值符合条件：构造一个stream[car值（满足条件的值）, 递归调用cdr值（延迟计算）]
            (else (stream-filter pred (stream-cdr stream))))) ; car值不符合条件：递归调用cdr值