; continuation 入门 02: call/cc（call-with-current-continuation）
; ---------------------------------------------------------------
; 先记一句最朴素的话：
; call/cc 会给你一个函数（下面都叫 exit），
; 你一旦调用 (exit 某值)，就会“立刻返回到 call/cc 外面”。
;
; 对初学者最实用的理解就是：exit 像“超级 return / 一键跳出”。

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
     ; exit 是“当前函数的出口按钮”。
     ; 在任何深度调用 (exit v)，都会让 find-first-even 直接返回 v。
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
     ; 重点：loop-y 在内层，loop-x 在外层，但 exit 两层都能一起跳出去。
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

(display "find-pair-sum-target '(1 4 7 10) '(2 3 9 11) target=13 => ")
(display (find-pair-sum-target '(1 4 7 10) '(2 3 9 11) 13))
(newline)

(display "find-pair-sum-target '(1 4) '(2 6) target=100 => ")
(display (find-pair-sum-target '(1 4) '(2 6) 100))
(newline)
(newline)

; ---------------------------------------------------------------
; 例子 3：从深层辅助函数里“直接失败返回”
; ---------------------------------------------------------------
; 有时你在深层函数里发现错误，希望整条流程马上结束。
; call/cc 的 exit 可以让你不用层层 if/return 往上传。
(define (sum-positive-list xs)
  (call/cc
   (lambda (exit)
     (define (loop ys acc)
       (cond ((null? ys) acc)
             ((< (car ys) 0)
              ; 一旦有负数，立刻失败并返回错误描述
              (exit "error: negative number found"))
             (else
              (loop (cdr ys) (+ acc (car ys))))))
     (loop xs 0))))

(display "sum-positive-list '(1 2 3) => ")
(display (sum-positive-list '(1 2 3))) ; 6
(newline)

(display "sum-positive-list '(1 -2 3) => ")
(display (sum-positive-list '(1 -2 3))) ; error: negative number found
(newline)

; 小结（先抓住这层就够）
; - 普通 return: 只能从当前函数末尾回来
; - exit(call/cc): 可以在深层位置“立刻跳回函数出口”
