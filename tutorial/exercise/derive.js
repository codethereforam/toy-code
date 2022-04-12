
var derive = (f) => {
        var deta = 0.00001
        var d= (x) => {return (f(x+deta) - f(x)) / (x+deta - x)} 
        return d
    }
var f = (x) => {return x * x} 
console.log(f(2))
var df = derive(f)
console.log(df(3))
var ddf = derive(df)
console.log(ddf(3))