; 符号求导

; 规则：
; dc/dx = 0
; dx/dx = 1
; d(u+v)/dx = du/dx + dv/dx
; d(uv)/dx = u(dv/dx) + v(du/dx)

(define (variable? expression)
    (symbol? expression))

(define (same-variable? exp1 exp2)
    (and (variable? exp1)
        (variable? exp2)
        (eqv? exp1 exp2)))

(same-variable? 'abc 'abc)

(define (sum? expression)
    (and (list? expression)
        (eqv? (car expression) '+)))

(define (=number? expression number)
    (and (number? expression)
        (= expression number)))

(define (make-sum exp1 exp2)
    (cond ((=number? exp1 0) exp2)
        ((=number? exp2 0) exp1)
        ((and (number? exp1) (number? exp2)) (+ exp1 exp2))
        (else (list '+ exp1 exp2))))

(define (addend expression)
    (cadr expression))

(define (augend expression)
    (caddr expression))

(define (product? expression)
    (and (list? expression)
        (eqv? (car expression) '*)))

(define (make-product exp1 exp2)
    (cond ((or (=number? exp1 0) (=number? exp2 0)) 0)
        ((=number? exp1 1) exp2)
        ((=number? exp2 1) exp1)
        ((and (number? exp1) (number? exp2)) (* exp1 exp2))
        (else (list '* exp1 exp2))))

; 被乘数，前者
(define (multiplicand expression)
    (cadr expression))

; 乘数，后者
(define (multiplier expression)
    (caddr expression))

(define (derive expression var)
    (cond ((number? expression) 0)
        ((variable? expression)
            (if (same-variable? expression var)
                1
                0))
        ((sum? expression)
            (make-sum (derive (addend expression) var)
                (derive (augend expression) var)))
        ((product? expression)
            (make-sum (make-product (multiplicand expression) 
                        (derive (multiplier expression) var))
                (make-product (multiplier expression) 
                        (derive (multiplicand expression) var))))
        (else  (error "unsupported expression" expression))))

(display "---------------\n")

(derive '1 'x)
(derive 'x 'x)
(derive 'y 'x)
(derive '(+ x 1) 'x)
(derive '(/ x y) 'x)

; g(x,y)=x*y*(x+3), g(x,y)/dx=2xy+3y
(define dgxy (derive '(* (* x y) (+ x 3)) 'x))
dgxy
(eval `(let ([x 4] [y 2]), dgxy))

; f(x)=x*x+3x+1, f(x)/d=2x + 3
(define dfx  (derive '(+ (+ (* x x) (* 3 x)) 1) 'x))
dfx
(eval `(let ([x 4]), dfx))

(define (derive-by-x expression value)
    (eval `(let ((x, value)), (derive expression 'x))))

(derive-by-x '(+ (+ (* x x) (* 3 x)) 1) 4)