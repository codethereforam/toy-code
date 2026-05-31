# Scheme continuation 入门（第一小步）

这组示例按“从易到难”排列，目标是先建立直觉：

1. `01-cps.scm`：超渐进版 CPS（Step1 到 Step4，适合第一次接触）
2. `02-callcc.scm`：白话理解 call/cc（提前退出、多层循环跳出、深层失败直返）
3. `03-shift-reset.rkt`：白话理解 shift/reset（只在 reset 范围内捕获后续）

---

## 你会先得到什么感觉

- **CPS**：函数不返回值，而是“把值交给下一步函数”
- **call/cc**：可以抓住当前控制流，像拿到一个“返回按钮”
- **shift/reset**：也是 continuation，但只抓当前 `reset` 边界内的控制流，更可控

---

## 建议学习顺序（30~60 分钟）

1. 先跑 `01-cps.scm`，严格按 Step1 -> Step4 看，不要跳
2. 再看 `02-callcc.scm`，按例子 1 -> 2 -> 3 看，先抓住“exit=超级 return”
3. 最后看 `03-shift-reset.rkt`，按例子 1 -> 2 -> 3 看，先把 `k` 当“洞位函数”

---

## 运行方式（按你的环境选择）

### 对 `.scm` 文件

在你使用的 Scheme REPL 中 `load` 对应文件即可，例如：

```scheme
(load "scheme/continuation/01-cps.scm")
(load "scheme/continuation/02-callcc.scm")
```

### 对 `.rkt` 文件（Racket）

```bash
racket scheme/continuation/03-shift-reset.rkt
```

---

## 和并发/异步的联系（先记住这一层就够了）

- continuation 本质上是在描述“暂停点后面还要做什么”
- coroutine / goroutine 调度器也在做类似的事：保存与恢复执行上下文
- Promise / async-await 也可以看成“把后续步骤封装后，在未来某时恢复执行”

先把这三份代码读通，你再往下看“事件循环、调度器、协程实现”会顺很多。
