const {outbound_csv_string} = require('./outbound_data.js');

class Outbound {
    constructor(shippingTime, stateName, quantity) {
        this.shippingTime = shippingTime;
        this.stateName = stateName;
        this.quantity = quantity;
    }
}

let o1 = new Outbound("2026-01-12 16:58","Illinois",  1);
let o2 = new Outbound("2025-12-03 13:53","New Jersey",  1);

// console.log(o1)
// console.log(o2)

let outbound_list_test = [o1, o2]

// 创建一个方法，将csv字符串转换为Outbound对象list
function parseOutboundCSV(csvString) {
    let lines = csvString.trim().split("\n");
    let outboundList = [];

    for (let line of lines) {
        let [stateName, shippingTime, quantity] = line.split(",");
        let outbound = new Outbound(shippingTime, stateName, parseInt(quantity));
        outboundList.push(outbound);
    }

    return outboundList;
}

// 示例CSV字符串
let csvData = `
California,2025-11-15 10:30,2
Texas,2025-12-01 14:20,3
Florida,2026-01-05 09:15,1
`;

// 解析CSV字符串并生成Outbound对象列表
let parsedOutboundList = parseOutboundCSV(outbound_csv_string);
// console.log(parsedOutboundList);

class MonthOutbound {
    constructor(yearMonth, stateName, quantity) {
        this.yearMonth = yearMonth;
        this.stateName = stateName
        this.quantity = quantity;
    }
}

// 将Outbound对象列表转换为MonthOutbound对象列表
function extractMonthlyOutbound(outboundList) {
    let monthOutboundList = [];

    for (let outbound of outboundList) {
        // 提取年月部分，格式为 "YYYY-MM"
        if (outbound.shippingTime !== "" && outbound.shippingTime.length >= 7) {
            let yearMonth = outbound.shippingTime.slice(0, 7); // 提取年月部分 "YYYY-MM"
            monthOutboundList.push(new MonthOutbound(yearMonth, outbound.stateName, outbound.quantity));
        }
    }

    return monthOutboundList;
}

let monthlyOutboundList = extractMonthlyOutbound(parsedOutboundList);
// console.log(monthlyOutboundList);

// 根据yearMonth和stateName对MonthOutbound进行分组汇总，
function groupMonthlyOutbound(monthOutboundList) {
    let groupedData = {};

    // 分组汇总
    for (let item of monthOutboundList) {
        let key = `${item.yearMonth}-${item.stateName}`;
        if (!groupedData[key]) {
            groupedData[key] = { ...item };
        } else {
            groupedData[key].quantity += item.quantity;
        }
    }

    return groupedData;
}

let groupedOutboundList = groupMonthlyOutbound(monthlyOutboundList);
// console.log(groupedOutboundList);

// 按yearMonth从小到大排序，总数量降序排序
function groupAndSortMonthlyOutbound(monthOutboundList) {
    // 按yearMonth从小到大排序，总数量降序排序
    return Object.values(monthOutboundList).sort((a, b) => {
        if (a.yearMonth === b.yearMonth) {
            return b.quantity - a.quantity;
        }
        return a.yearMonth.localeCompare(b.yearMonth);
    });
}

let finalSortedOutboundList = groupAndSortMonthlyOutbound(groupedOutboundList);
console.log(finalSortedOutboundList);


