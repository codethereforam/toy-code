# 需求
3.18-3.24’如果显示预约，就提醒

# 接口
curl 'https://mbapp.jsph.org.cn/mobile/online/GetReservationScheduleNew_H5.do?mCUs1Q7w=0uVrXpAlqWttALnFkdxIuwweYMK0o5FQcgh36Tg.uSs0dA3POq1IU.zyKL_WWqO94zSGI.7pHwnr7HXSFV9nvHX51i0T8GOlmE2WyPqihr41I3UmCH0KcbG' \
-H 'Content-Type: application/json; charset=UTF-8' \
--data-raw '{"content":{"ResourceTypeID":"002","DiagOrgID":"","ResourceID":"7286","HospID":"0101|0102|0106","ReserDate":""}}'

# 响应示例
```json
{
  "header": {
    "resultCode": "0",
    "resultMsg": "成功",
    "hisCode": null,
    "hisMsg": null
  },
  "content": [
    {
      "CurDate": "2026-03-09",
      "DateTypeID": "100101",
      "LimitAmout": "30",
      "HuiMinFlag": "0",
      "ResourceName": "马翔",
      "OrgName": "生殖中心医大门诊",
      "OrgAddr": "生殖中心医大门诊(永庆村16号)",
      "ReserveFlag": "1",
      "HospID": "0106",
      "OrgID_Used": "010601010101",
      "EmpTitleID": "231",
      "DateTypeName": "星期一上午",
      "ResourceID": "7286",
      "ResourceTypeID": "002000",
      "RemainAmount": "0",
      "TotAmount": "35.00",
      "PauseFlag": "0",
      "EmpID_C": "D320106003071",
      "PeriodFlag": "1"
    }]
}
```

# 响应说明
* "CurDate": "2026-04-02", --- 预约日期
* "RemainAmount": "11" --- 余号
* "ResourceName": "马翔", --- 预约医生

# 开发说明
- 代码写到AppointmentNotice260305.java，只用一个类，配置项放到开头final，加todo
- 预约日期在3.18-3.24之间，且余号大于0，就发邮件通知，这部分逻辑参考appointment_notice.py，每隔1分钟执行一次（可配）