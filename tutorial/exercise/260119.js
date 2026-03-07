var f1 = ( x, y) => 2 * x + y;
var r1 = f1(1, 10);
console.log(r1);
console.log(f1(2, 20));

// 变量a variable
var a = 2;
var b= 4;
var c = f1(a, b);
console.log(c);

function function1(x, y) {
    let step1 = 2 * x;
    let step2 = step1 + y;
    return step2;
}

var f11 = ( x, y) => {
   return  2 * x + y;
}

var function2 = function1;

var v1 = 1;
var v2 =  "hello world";
var v3 = true;
var v4 = 3 < 1;
var v1 = 1.23;

console.log(v1);
console.log(v2);
console.log(v3);
console.log(v4);

// if else

// 循环

// 递归

var outboud_data = `
New York,2025-11-04 07:35,1
Illinois,2025-12-27 11:26,1
`;

// 类

class Outbound {
    constructor(stateName, shippingTime, quantity) {
        this.stateName = stateName;
        this.shippingTime = shippingTime;
        this.quantity = quantity;
    }
}

var o1 = new Outbound("New York","2025-11-04 07:35",1)
var o2 = new Outbound("Illinois","2025-12-27 11:26",1)

console.log(o1)
console.log(o2)

class WarehouseOutbound {
    constructor(warehouseName, outbound) {
        this.warehouseName = warehouseName;
        this.outbound = outbound;
    }
}

var wo1 = new WarehouseOutbound("Warehouse A", o1);
console.log(wo1);

// array / list
var outbound_list_test = [o1, o2]
console.log(outbound_list_test);
console.log(outbound_list_test.length);
console.log(outbound_list_test[0]);
console.log(outbound_list_test[1]);

let test_array = [];
test_array.push(1);
test_array.push(2);
test_array.push(3);
console.log(test_array);
console.log(test_array.length);

// String operations
var s1 = "New York,2025-11-04 07:35,1";
console.log(s1.length);
// 获取第10个字符
console.log(s1[9]);
// 获取第10个到第20个字符
console.log(s1.slice(9, 19));

var o3 = parseOutboundString(s1);
console.log(o3)

function parseOutboundString(str) {
    // 根据逗号分割字符串
    var parts = split(str, ",");
    return new Outbound(parts[0], parts[1], parseInt(parts[2]));
}

function split(str, delimiter) {
    var parts = [];

    var tmpStr = "";
    for (let i = 0; i < str.length; i++) {
        let ch = str.charAt(i);
        if (ch === delimiter) {
            parts.push(tmpStr);
            tmpStr = "";
        } else {
            tmpStr += ch;

            if (i === str.length - 1) {
                parts.push(tmpStr);
            }
        }
    }

    return parts;
}

var t1 = new Outbound("New York","2025-11-04 07:35",1)
var t2 = new Outbound("Illinois","2025-12-27 11:26",3)
var t3 = new Outbound("New York","2025-10-01 11:26",5)
var t4 = new Outbound("Illinois","2025-09-01 11:26",2)

var outbound_array_1 = [t1, t2, t3, t4]

// 需求1：数量>2 放到一个新的数组里

//写一个函数，传入原数组，输出数量>2的新数组

var f= ( x ) => {
    var y = []
    for (var i= 0; i < x.length; i++) {
        if ( x [ i ].quantity > 2) {
            y.push( x [ i ] );
        } else {

        }
    }
    return  y;
}
console.log(2026);
console.log(f(outbound_array_1));

function filterByQuantity(outboundArray, minQuantity) {
    let result = [];
    for (let outbound of outboundArray) {
        if (outbound.quantity > minQuantity) {
            result.push(outbound);
        }
    }
    return result;
}

var filteredByQuantity = filterByQuantity(outbound_array_1, 2);
console.log("Filtered by Quantity > 2:");
console.log(filteredByQuantity);



// 需求2：州=Illinois

function filterByStateName(outboundArray, BaseStateName) {
    let result = [];
    for (let outbound of outboundArray) {
        if (outbound.stateName === BaseStateName){
            result.push(outbound);
        }
    }
    return result;
}

var filteredByStateName = filterByStateName(outbound_array_1,"New York");
console.log("--Illinois--");
console.log(filteredByStateName);

// 需求3：写一个函数，支持传入Outbound Array，并传入一个过滤函数Outbound（比如判断数量>2，州=Illinois）

function filter(outboundArray, fa) {
    let result = [];
    for (let outbound of outboundArray) {
        if (fa(outbound) === true){
            result.push(outbound);
        }
    }
    return result;
}

// 作用：判断出库单对象的数量是否大于数量基准
// 参数：出库单对象
// 返回类型：布偶
var fb= ( xq, minQuantity) => {
    if (xq.quantity > minQuantity){
        return true;
    } else {
        return false;
    }
}

var fc= ( xn ) => {
    if (xn.stateName ===  "Illinois"){
        return true;
    } else {
        return false;
    }

}

var fd = (xn) =>  fb(xn, 2);
var filtered1 = filter(outbound_array_1, fd);

var filtered2 = filter(outbound_array_1,fc);

var filtered3 = filter(outbound_array_1,( xq) => xq.quantity < 2);

console.log("-------");
console.log(filtered1);
console.log(filtered2);
console.log(filtered3);


var findGt2 = (outbound) => {
    return fb(outbound, 2);
}

console.log("===============")
console.log(filter(outbound_array_1, findGt2))


// 返回值是函数的例子
// 这个函数返回一个新的过滤函数
function createQuantityFilter(minQuantity) {
    let f = (outbound) => {
        return outbound.quantity > minQuantity;
    };
    return f;
}

var findGt3 = createQuantityFilter(3);
var findGt4 = createQuantityFilter(4);

console.log("数量>3的出库单:");
console.log(filter(outbound_array_1, findGt3));
console.log("数量>4的出库单:");
console.log(filter(outbound_array_1, createQuantityFilter(4)));

// function call itself
