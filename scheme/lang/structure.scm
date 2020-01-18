(define-record-type user (fields name age say-hello)) 
(define u1
    (make-user 
        "u1" 
        12 
        (lambda () (display "hi"))))
(user? u1)
(user-name u1)
(user-age u1)
((user-say-hello u1))