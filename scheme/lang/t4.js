var square = (x) => x * x
console.log(square(7))

var f = (x) => square(x) + 1
console.log(f(7))

var f1 = (x, y) => {
        if(y == 1) {
                return x * x
        } else {
                return x + 1
        }
}
console.log(f1(2, 1))
console.log(f1(4, 2))

var factorial = (x) => {
        if(x == 1) {
                return 1
        } else {
                return x * factorial(x - 1)
        }
}
console.log(factorial(5))

var factorial3 = (res,i,max) => {
        if(i > max) {
                return res
        } else {
                return factorial3(res*i,i+1,max)
        }
}
console.log(factorial3(1,1,5))
var f3 = (x) => factorial3(1,1,x)
console.log(f3(7))

var v1 = 1;
var ff = (x, y) => {
        var res = 2;
        return res + v1 + x + y;
} 

var factorialf = (x) => {
        var res = 1
        for (i = 1; i <= 5; i=i+1) {
             res = i * res
        }
        return res
}
console.log(factorialf(5))

// time(t) = tx
// f = times(2) = 2x; f(5)
// times(3) = 3x
{
        var times = (t) => {
                        temp = x => t * x
                        return temp
                }
        // var times = (t) =>  x => t * x;
        var double = times(2)
        var triple = times(3)
        console.log(double(9))
        console.log(triple(8))
}

// fPlusOne(f, x) = f(x) + 1
// fPlusOne(x^2, 2) = 2^ 2 + 1
// fPlusOne(x + 1, 2) = 2 + 1 + 1

{
        var fPlusOne = (t, x) => {
                        return t(x) + 1
                }
        var g = x => x + 1
        var f = x => x * x
        var f1 = fPlusOne(g,6)
        var f2 = fPlusOne(f,9)
        console.log(f1)
        console.log(f2)
}