(let countdown 
        ((i 10))
        (if (= i 0) 'end
            (begin
                (display i)
                (newline)
                (countdown (- i 1))
            )
        )           
)