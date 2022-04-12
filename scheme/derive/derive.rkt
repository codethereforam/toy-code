; 模式匹配求导

; #lang racket

; 规则：
; dc/dx = 0
; dx/dx = 1
; d(u+v)/dx = du/dx + dv/dx
; d(uv)/dx = u(dv/dx) + v(du/dx)

(define (same-variable? e1 e2)
    (and (symbol? e1)
        (symbol? e2)
        (eqv? e1 e2)))

(define (=number? expr number)
    (and (number? expr)
        (= expr number)))

(define (sum e1 e2)
    (cond ((=number? e1 0) e2)
        ((=number? e2 0) e1)
        ((and (number? e1) (number? e2)) (+ e1 e2))
        (else (list '+ e1 e2))))

(define (multi e1 e2)
    (cond ((or (=number? e1 0) (=number? e2 0)) 0)
        ((=number? e1 1) e2)
        ((=number? e2 1) e1)
        ((and (number? e1) (number? e2)) (* e1 e2))
        (else (list '* e1 e2))))

(define (derive expr var)
    (match expr 
        [(? number? x) 0]
        [(? symbol? s)
            (if (same-variable? s var) 1 0)]
        [`(+ ,e1 ,e2)
            (sum (derive e1 var) 
                (derive e2 var))]
        [`(* ,e1 ,e2)
            (sum (multi e1 (derive e2 var))
                (multi e2 (derive e1 var)))]
        (else  (error "unsupported expr" expr))))

; ---------------

; f(x)=x*x+x+1, f(x)/d=2x+1
(define f '(+ (+ (* x x) x) 1))
(define df (derive f 'x))
df
(eval `(let ([x 3]), df))

(let ([x 2])
  (let ([f (lambda (y) (* x y))])
    (let ([x 4])
      (f 3))))
