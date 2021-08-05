// 函数作为另一个函数的参数和返回值
{
    var compose =
        (f, g) =>
            (x => f(g(x)))
    var g = x => x + 1
    var f = x => x * x
    var t = compose(f, g)
    console.log(t(2))
}

// 面向对象
{
    const person = {
        name: "tony",
        age: 18,
        isMale: true,
        sayHello: () => {
            console.log("hello");
        },
        selfIntroduce: () => {
            console.log("my name is " + this.name);
        }
    };

    console.log(person.name)
    person.sayHello()
    person.selfIntroduce()
}