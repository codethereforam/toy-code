#lang racket
; continuation 入门 03: shift/reset（定界 continuation）
; --------------------------------------------------------
; 需要 Racket 的控制操作库：
(require racket/control)

; 对比 call/cc：
; - call/cc 捕获“整个剩余计算”（直到最外层）；
; - shift/reset 只捕获 reset 块内的剩余计算（定界/局部化）。
;
; 这类能力常用于：
; - 高级控制流抽象
; - 轻量协程/回溯搜索
; - effect handler 的底层机制理解

; --------------------------------------------------------
; 例子 1：最小直觉
; --------------------------------------------------------
; 在 reset 内，shift 捕获“(+ 1 [这里])”这个上下文为 k。
; 所以 (k 10) 等价于把 10 填回去：(+ 1 10) => 11
(define ex1
  (reset
    (+ 1
       (shift k
         (k 10)))))

(displayln (format "ex1 => ~a" ex1)) ; 11

; --------------------------------------------------------
; 例子 2：同一个 continuation 可被复用多次
; --------------------------------------------------------
; k 表示“把一个值放回 (+ 1 [ ])”
; - (k 10) => 11
; - (k 20) => 21
; 最后 shift 的返回值是 11 + 21 = 32
(define ex2
  (reset
    (+ 1
       (shift k
         (+ (k 10)
            (k 20))))))

(displayln (format "ex2 => ~a" ex2)) ; 32

; --------------------------------------------------------
; 例子 3：和“异步组合”直觉对应
; --------------------------------------------------------
; 你可以把 k 理解成一个“后续 pipeline”：
; shift 可以决定：
; - 什么时候调用 pipeline
; - 调几次 pipeline
; - 用什么值调用 pipeline
;
; 这和 Promise chain / async 调度中“延后继续执行”的思想非常接近。
(define (demo-pipeline x)
  (reset
    (* 2
       (shift k
         ; 先做一次普通继续
         (define a (k x))
         ; 再做一次“变形后继续”
         (define b (k (+ x 10)))
         (+ a b)))))

(displayln (format "demo-pipeline 5 => ~a" (demo-pipeline 5)))
