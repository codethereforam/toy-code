; continuation 入门 02: call/cc（call-with-current-continuation）
; ---------------------------------------------------------------
; call/cc 可以捕获“当前 continuation”（也就是当前剩余计算）。
; 你可以把它理解成：
; - 拍下当前执行现场；
; - 在未来某个时刻，跳回这个现场，并给它一个值。

; ---------------------------------------------------------------
; 例子 1：提前退出（early exit）
; ---------------------------------------------------------------
; 在列表里找第一个偶数：
; - 找到了就立即返回，不再遍历后续元素；
; - 如果没找到，返回 #f。
;
; 这和很多语言中的 “break + return” 非常像，
; 但这里通过 call/cc 显式地抓取“退出点”实现。
(define (find-first-even xs)
  (call/cc
   (lambda (exit)
     ; exit 是一个 continuation 函数：
     ; 调用 (exit v) 会立刻返回 find-first-even，返回值就是 v。
     (define (loop ys)
       (cond ((null? ys) #f)
             ((even? (car ys)) (exit (car ys)))
             (else (loop (cdr ys)))))
     (loop xs))))

(display "find-first-even '(1 3 5 8 10) => ")
(display (find-first-even '(1 3 5 8 10)))
(newline)

(display "find-first-even '(1 3 5 7 9)  => ")
(display (find-first-even '(1 3 5 7 9)))
(newline)

; ---------------------------------------------------------------
; 例子 2：一次跳出多层循环（多层 break）
; ---------------------------------------------------------------
; 很多语言里，跳出“多层循环”比较麻烦。
; call/cc 可以一次性拿到“函数出口”，在任意深度直接跳出去。
(define (find-pair-sum-target xs ys target)
  (call/cc
   (lambda (exit)
     ; 先遍历 xs，再遍历 ys，找到后直接 exit。
     (define (loop-x as)
       (if (null? as)
           #f
           (letrec
               ((loop-y
                 (lambda (bs)
                   (if (null? bs)
                       #f
                       (if (= (+ (car as) (car bs)) target)
                           (exit (list (car as) (car bs)))
                           (loop-y (cdr bs)))))))
             (loop-y ys)
             (loop-x (cdr as)))))
     (loop-x xs))))

(display "find-pair-sum-target => ")
(display (find-pair-sum-target '(1 4 7 10) '(2 3 9 11) 13))
(newline)
