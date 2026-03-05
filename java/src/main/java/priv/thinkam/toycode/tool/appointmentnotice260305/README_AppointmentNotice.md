# 预约提醒程序 (AppointmentNotice260305)

## 功能说明
检查医生预约情况，当发现预约日期在3月18日-3月24日之间且有余号时，自动发邮件通知。

## 技术栈
- **Jackson**: JSON序列化/反序列化
- **Lombok**: 简化POJO类代码
- **JavaMail**: 邮件发送
- **LocalDate**: 日期判断

## 核心逻辑
1. 每隔1分钟调用一次API获取预约信息
2. 使用Jackson反序列化JSON响应为Java对象
3. 使用Stream API过滤符合条件的预约记录：`预约日期在3.18-3.24之间 AND 余号 > 0`
4. 如果找到符合条件的预约，发送邮件通知

## 配置说明

### 邮件配置
```java
private static final String SENDER_EMAIL = "";                    // TODO: 发件人邮箱
private static final String SENDER_PASSWORD = "";                 // TODO: 邮箱密码
private static final String RECIPIENT_EMAIL = "codethereforam@gmail.com";
private static final String SMTP_SERVER = "smtp.163.com";
private static final int SMTP_PORT = 25;
```

### 预约日期范围
```java
private static final LocalDate START_DATE = LocalDate.of(2026, 3, 18);
private static final LocalDate END_DATE = LocalDate.of(2026, 3, 24);
```

### 执行间隔
```java
private static final long INTERVAL_MINUTES = 1;  // 每1分钟执行一次
```

## 编译运行

### 1. 刷新Maven依赖
```bash
mvn clean compile
```

### 2. 运行程序
```bash
mvn exec:java -Dexec.mainClass="AppointmentNotice260305"
```

或者在IDE中直接运行main方法

## 依赖
```xml
<dependency>
    <groupId>com.sun.mail</groupId>
    <artifactId>javax.mail</artifactId>
    <version>1.6.2</version>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.15.2</version>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.datatype</groupId>
    <artifactId>jackson-datatype-jsr310</artifactId>
    <version>2.15.2</version>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.8</version>
</dependency>
```

## 代码结构

### 核心方法
| 方法名 | 说明 |
|--------|------|
| `main()` | 主程序入口，循环检查预约 |
| `checkAndNotify()` | 获取数据、过滤并发送通知 |
| `isAvailable()` | 判断预约是否满足条件 |
| `fetchReservationData()` | 调用API获取预约数据 |
| `sendEmail()` | 发送邮件通知 |

### 数据模型
- **ApiResponse**: API响应根对象
- **Header**: 响应头信息
- **Appointment**: 预约信息（使用@Data和@JsonProperty简化代码）

## 优势
1. **简洁可读**: 使用Lombok的@Data注解，减少样板代码
2. **类型安全**: 使用Jackson反序列化为强类型对象
3. **易于维护**: LocalDate处理日期逻辑清晰
4. **函数式风格**: Stream API过滤数据，代码更优雅

## 示例输出
```
预约提醒程序启动，监控日期范围：2026-03-18 ~ 2026-03-24
找到预约：医生=马翔 日期=2026-03-20 余号=5
发现可预约号源，邮件已发送！
```

