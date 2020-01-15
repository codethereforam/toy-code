(letrec ((countdown 
                (lambda (i) 
                        (if (= i 0) 'end
                            (begin
                                (display i)
                                (newline)
                                (countdown (- i 1))
                            )
                        )
                )
        ))
        (countdown 10)
)