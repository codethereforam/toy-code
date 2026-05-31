; continuation 入门 01: CPS（超渐进版）
; ===============================================================
; 如果你只看懂了“直接返回”，这份就是给你的：
; Step 1 -> Step 2 -> Step 3，慢慢把 CPS 直觉建立起来。
;
; 一句话先记住：
; CPS 不是“返回值”，而是“把结果交给 k（下一步函数）”。

; ---------------------------------------------------------------
; Step 1. 从最小例子开始：把一个数 +1
; ---------------------------------------------------------------

; 1) 普通写法：直接 return
(define (add1-direct x)
  (+ x 1))

; 2) CPS 写法：不 return，改成调用 k
;   - k 表示“拿到结果后要做什么”
(define (add1-cps x k)
  (k (+ x 1)))

(display "Step1 add1-direct 5 => ")
(display (add1-direct 5)) ; 6
(newline)

; 传入“恒等 continuation”：
; (lambda (v) v) = “拿到什么就原样给回去”
(display "Step1 add1-cps    5 => ")
(display (add1-cps 5 (lambda (v) v))) ; 6
(newline)

; 传入“自定义 continuation”：
; 这里拿到 6 后再 *10，结果是 60
(display "Step1 add1-cps with custom k => ")
(display (add1-cps 5 (lambda (v) (* v 10)))) ; 60
(newline)
(newline)

; ---------------------------------------------------------------
; Step 2. 再看列表求和（你已经看懂的 direct 版本）
; ---------------------------------------------------------------
(define (sum-list-direct xs)
  (if (null? xs)
      0
      (+ (car xs) (sum-list-direct (cdr xs)))))

(display "Step2 sum-list-direct '(1 2 3 4 5) => ")
(display (sum-list-direct '(1 2 3 4 5))) ; 15
(newline)
(newline)

; ---------------------------------------------------------------
; Step 3. 把 Step2 改成 CPS（核心）
; ---------------------------------------------------------------
; sum-list-cps 的参数：
; - xs: 还没处理的列表
; - k : “最终结果出来后该怎么办”
;
; base case:
;   空列表的和是 0，但 CPS 不直接返回 0，而是 (k 0)
;
; recursive case:
;   先去算 (cdr xs) 的和。
;   算完后会得到 tail-sum，再把 (car xs) 加上去，
;   最后把新结果交给旧的 k。
(define (sum-list-cps xs k)
  (if (null? xs)
      (k 0)
      (sum-list-cps
       (cdr xs)
       (lambda (tail-sum)
         (k (+ (car xs) tail-sum))))))

; ---------------- 这段最容易卡住：补一个“执行对照图” ----------------
; 以 (1 2 3) 为例：
;
; [普通递归]
;   (+ 1 (sum '(2 3)))
;   (+ 1 (+ 2 (sum '(3))))
;   (+ 1 (+ 2 (+ 3 (sum '()))))
;   (+ 1 (+ 2 (+ 3 0)))
;   => 6
;
; 直觉：普通递归把“还没做的加法”压在调用栈里，等 return 时再一层层做。
;
; [CPS]
;   初始 k0(v) = v
;   看到 1，生成 k1(v) = k0(1 + v)
;   看到 2，生成 k2(v) = k1(2 + v)
;   看到 3，生成 k3(v) = k2(3 + v)
;   到空表后，不 return 0，而是执行 (k3 0)
;     k3(0) -> k2(3) -> k1(5) -> k0(6) -> 6
;
; 直觉：CPS 不靠 return 回来，而是把“后续要做的事”打包进 k，最后统一执行。
; ----------------------------------------------------------------

; 先用恒等 k，验证和 direct 一样
(display "Step3 sum-list-cps identity k      => ")
(display (sum-list-cps '(1 2 3 4 5) (lambda (v) v))) ; 15
(newline)

; 再用自定义 k：求和后额外 +100
(display "Step3 sum-list-cps custom k (+100) => ")
(display (sum-list-cps '(1 2 3 4 5) (lambda (v) (+ v 100)))) ; 115
(newline)
(display "Step3 sum-list-cps '(1 2 3) identity => ")
(display (sum-list-cps '(1 2 3) (lambda (v) v))) ; 6
(newline)
(newline)

; ---------------------------------------------------------------
; Step 4. “双 continuation”= 成功/失败两条后续流程
; ---------------------------------------------------------------
; 这一步就是你将来理解 Promise/async 的桥梁：
; - ok  = 成功后做什么（像 resolve 分支）
; - err = 失败后做什么（像 reject 分支）
(define (safe-div-cps a b ok err)
  (if (= b 0)
      (err "division by zero")
      (ok (/ a b))))

(display "Step4 safe-div-cps 10 2 => ")
(safe-div-cps
  10
  2
  (lambda (v)
    (display "ok, result = ")
    (display v)
    (newline))
  (lambda (msg)
    (display "err, message = ")
    (display msg)
    (newline)))

(display "Step4 safe-div-cps 10 0 => ")
(safe-div-cps
  10
  0
  (lambda (v)
    (display "ok, result = ")
    (display v)
    (newline))
  (lambda (msg)
    (display "err, message = ")
    (display msg)
    (newline)))

; ---------------------------------------------------------------
; 读到这里你应该形成这三个感觉：
; 1) k 就是“下一步”
; 2) CPS 的威力在于：调用方可自定义“下一步”
; 3) 双 continuation 天然表达“成功/失败”分支
; ---------------------------------------------------------------