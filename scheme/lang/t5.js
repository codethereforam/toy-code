
// 小于n的素数个数
var countPrimeNumber = (x) => {
    var total = 0;
    for (i = 2; i <= x; i=i+1) {
        total  = total + primeJudge(i)
   }
    return total;
}

//是否为素数，为素数 primeJudge(t) = 1
var primeJudge =(t) => {
        var numberTotal = 0;
        for (var i = 2; i < t; i=i+1) {
                var numberI= isZhengShu(i,t)
                numberTotal = numberTotal +numberI
                 //不等于0，说明有因子，不为素数
                if(numberTotal != 0) {
                        return 0
                       
                } else{
                        //等于0，未循环结束，不能判断有无因子，继续循环 
                }
        }
        if(numberTotal == 0) {
                return 1
        } else {
                return 0
        }
}


// (t/i)是否为整数
var isZhengShu =(i,t) => {
        if(t % i == 0) {
                return 1
        } else {
                return 0
        }
}

console.log(countPrimeNumber(-100) == 0)
console.log(countPrimeNumber(1) == 0)
console.log(countPrimeNumber(2) == 1)
console.log(countPrimeNumber(5) == 3)
console.log(countPrimeNumber(15) == 6)
console.log(countPrimeNumber(20) == 8)
console.log(countPrimeNumber(500))