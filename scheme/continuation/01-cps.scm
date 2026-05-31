; continuation 入门 01: CPS（Continuation-Passing Style）
; -------------------------------------------------------
; 核心思想：
; 1) 普通函数“直接返回结果”；
; 2) CPS 函数“不直接返回”，而是把结果交给参数 k（continuation）。
; k 就是“接下来要做的事”。

; ---------------------------
; 例子 1：普通风格（direct style）
; ---------------------------
(define (sum-list-direct xs)
  (if (null? xs)
      0
      (+ (car xs) (sum-list-direct (cdr xs)))))

; ---------------------------
; 例子 2：CPS 风格
; ---------------------------
; 参数说明：
; - xs: 输入列表
; - k : continuation，接收“sum-list 的结果”
;
; 当 xs 为空时，不返回 0，而是调用 (k 0)。
; 当 xs 非空时，递归拿到 tail-sum 后，再交给新的 continuation。
(define (sum-list-cps xs k)
  (if (null? xs)
      (k 0)
      (sum-list-cps
       (cdr xs)
       (lambda (tail-sum)
         (k (+ (car xs) tail-sum))))))

; 使用 CPS：最常见入口是传入“恒等 continuation”
; (lambda (v) v) 表示“结果就这样返回给调用者”
(define sum1 (sum-list-direct '(1 2 3 4 5)))
(define sum2 (sum-list-cps '(1 2 3 4 5) (lambda (v) v)))

(display "sum-list-direct => ")
(display sum1)
(newline)

(display "sum-list-cps    => ")
(display sum2)
(newline)

; -------------------------------------------
; 例子 3：双 continuation（成功/失败）风格
; -------------------------------------------
; 这个模式和 async/await + Promise 的 then/catch 十分接近：
; - ok  类似 resolve
; - err 类似 reject
;
; safe-div-cps 不抛异常：而是把“分支控制权”交给 continuation。
(define (safe-div-cps a b ok err)
  (if (= b 0)
      (err "division by zero")
      (ok (/ a b))))

; 成功分支
(safe-div-cps
  10
  2
  (lambda (v)
    (display "safe-div-cps ok  => ")
    (display v)
    (newline))
  (lambda (msg)
    (display "safe-div-cps err => ")
    (display msg)
    (newline)))

; 失败分支
(safe-div-cps
  10
  0
  (lambda (v)
    (display "safe-div-cps ok  => ")
    (display v)
    (newline))
  (lambda (msg)
    (display "safe-div-cps err => ")
    (display msg)
    (newline)))
