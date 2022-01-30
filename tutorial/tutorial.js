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
    const person1 = {
        name: "tony",
        age: 18,
        isMale: true,
        sayHello: () => {
            console.log("hello");
        },
        selfIntroduce() {
            console.log("my name is " + this.name);
        }
    };

    console.log(person1.name)
    person1.sayHello()
    person1.selfIntroduce()


}

/*
 * 数据
 * 1. 定义类
 * 2. 数组 
 */
{
    class Person {

        constructor(name, age, isMale) {
            this.name = name;
            this.age = age;
            this.isMale = isMale;
        }

        selfIntroduce() {
            console.log("my name is " + this.name);
        }
    }

    var p1 = new Person("YY", 18, false);
    console.log(p1.name)
    p1.selfIntroduce()

    var p2 = new Person("AY", 19, false);
    console.log(p2.name)
    p2.selfIntroduce()

    
    let persons = [p1, p2]
    console.log(persons[1].name)
    console.log(persons.length)

    for (i = 0; i < persons.length; i++) {
        console.log("No." + i + ": " + persons[i].name)
   }
}