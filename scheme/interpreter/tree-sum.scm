;'((1 2) (3 4))
(define (tree-sum expr)
	(cond ((number? expr) expr)
		(else (+ 
			(tree-sum (car expr)) 
			(tree-sum (car (cdr expr)))))))
(tree-sum '(1 2))
(tree-sum '((1 2) (3 4)))
(tree-sum '((1 2) ((3 4) 6)))