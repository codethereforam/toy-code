; 计算器
(define (calc expr)
	(cond ((number? expr) expr)
		(else (let ([op (car expr)] 
					[v1 (calc (cadr expr))] 
					[v2 (calc (caddr expr))])
			(cond ((eqv? op '+) (+ v1 v2))
				  ((eqv? op '-) (- v1 v2))
				  ((eqv? op '*) (* v1 v2))
				  ((eqv? op '/) (/ v1 v2)))))))
			
(calc '(* (+ 1 2) (- 4 2)))
(calc '(* (+ 1 2) (+ (/ 6 2) 1)))