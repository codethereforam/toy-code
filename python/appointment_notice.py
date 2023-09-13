import requests
from bs4 import BeautifulSoup
import time
import smtplib
from email.mime.text import MIMEText


# 检查响应状态码是否为200，表示请求成功
def can_book(html_content):
    # 使用Beautiful Soup解析HTML
    soup = BeautifulSoup(html_content, 'html.parser')

    div = soup.find('div', class_='yy_paiban')
    # print(html_content)
    # 找到表头中包含日期"09-16"的表头单元格
    target_headers = div.select(f'th:-soup-contains("09-16")')
    target_header = target_headers[0]

    header_columns = div.find_all('th')
    column_index = header_columns.index(target_header)
    second_row = div.find('tbody').find_all('tr')[0]
    appointment_status_cell = second_row.find_all('td')[column_index]
    appointment_status_str = appointment_status_cell.text.strip()
    if '已满' in appointment_status_str:
        return False
    elif '预约' in appointment_status_str:
        return True
    else:
        raise AssertionError("代码有问题")


url = "https://www.nj12320.org/njres/reservation/doc_detail.do?docid=375814&hoscode=32010100-0"

# 添加模拟浏览器的请求头，以避免被网站识别为爬虫
headers = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36"
}


# 循环直到can_book返回True
def notice():
    # 发件人和收件人信息
    sender_email = ""
    recipient_email = "codethereforam@gmail.com"

    # SMTP服务器信息
    smtp_server = "smtp.163.com"
    smtp_port = 25

    # 发件人的登录信息
    sender_username = ""
    sender_password = ""

    # 创建一个SMTP连接
    server = smtplib.SMTP(smtp_server, smtp_port)
    # server.starttls()  # 使用TLS加密连接

    # 登录到SMTP服务器
    server.login(sender_username, sender_password)

    # 创建邮件内容
    subject = "预约通知"
    body = "可预约"
    message = MIMEText(body, "plain")
    message["From"] = sender_email
    message["To"] = recipient_email
    message["Subject"] = subject

    # 发送邮件
    server.sendmail(sender_email, recipient_email, message.as_string())
    # 关闭SMTP连接
    server.quit()
    print("Email sent successfully")


# notice()


while True:
    # 发送GET请求
    response = requests.get(url, headers=headers)
    if response.status_code == 200:
        # 获取HTML内容
        html_content = response.text
        is_can_book = can_book(html_content)
        if is_can_book:
            # 如果can_book返回True，执行发邮件操作
            print("可以预约，发送邮件")
            notice()
            break  # 结束循环
        else:
            print("暂时不能预约，等待3分钟...")
            time.sleep(180)  # 等待10分钟（3 * 60秒）
    else:
        print("请求失败，状态码:", response.status_code)

print("循环结束")
