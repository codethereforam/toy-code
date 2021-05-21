# toy-code

some toy code when I learn programming

## 1. [SICP学习-java版](/java/src/main/java/priv/thinkam/toycode/sicp)

## 2. [scheme语言学习](/scheme)

## 3. 问题探索
### 3.1 探索计算小于n的素数个数不同并发方式的效率
#### 实现版本
- [java版本](/java/src/main/java/priv/thinkam/toycode/concurrent/primecount)
    - 线程 + 锁
    - 函数式
    - fork-join
    - 多线程共享BlockingQueue
    - 基于future的并行
- [go版本](/go/primecount)
    - CSP(goroutine + channel)
#### 总结
- 参考书籍：《七周七并发模型》
- 已实现并发模型
    - 线程 + 锁
    - 函数式
    - CSP
- 未实现并发模型
    - actor
    - lambda架构（分布式map-reduce）
