package priv.thinkam.toycode.tool.appointmentnotice260305;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Data;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * 预约提醒程序
 * 功能：检查指定医生的预约情况，当预约日期在3.18-3.24之间且有余号时发邮件通知
 */
public class AppointmentNotice260305 {
    // TODO: 配置项 - 邮件设置
    private static final String SENDER_EMAIL = "";                               // 发件人邮箱
    private static final String SENDER_PASSWORD = "";                            // 邮箱密码
    private static final String RECIPIENT_EMAIL = "";    // 收件人邮箱
    private static final String SMTP_SERVER = "smtp.163.com";
    private static final int SMTP_PORT = 25;

    // TODO: 配置项 - API设置
    private static final String API_URL = "https://mbapp.jsph.org.cn/mobile/online/GetReservationScheduleNew_H5.do?mCUs1Q7w=0uVrXpAlqWttALnFkdxIuwweYMK0o5FQcgh36Tg.uSs0dA3POq1IU.zyKL_WWqO94zSGI.7pHwnr7HXSFV9nvHX51i0T8GOlmE2WyPqihr41I3UmCH0KcbG";
    private static final String REQUEST_BODY = "{\"content\":{\"ResourceTypeID\":\"002\",\"DiagOrgID\":\"\",\"ResourceID\":\"7286\",\"HospID\":\"0101|0102|0106\",\"ReserDate\":\"\"}}";

    // TODO: 配置项 - 预约日期范围（3月18日-3月24日）
    private static final LocalDate START_DATE = LocalDate.of(2026, 3, 18);
    private static final LocalDate END_DATE = LocalDate.of(2026, 3, 24);
    //private static final LocalDate END_DATE = LocalDate.of(2026, 4, 1);

    // TODO: 配置项 - 执行间隔（秒）
    private static final long INTERVAL_SECONDS = 30;

    private static final ObjectMapper MAPPER = new ObjectMapper()
//            .registerModule(new JavaTimeModule()
//                    .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DEFAULT_DATETIME_FORMATTER))
//                    .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DEFAULT_DATETIME_FORMATTER)))
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            // 否则对象没字段会报错
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            // 否则JSON有多余的字段会报错
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        System.out.println("预约提醒程序启动，监控日期范围：" + START_DATE + " ~ " + END_DATE);
        while (true) {
            try {
                checkAndNotify();
                TimeUnit.SECONDS.sleep(INTERVAL_SECONDS);
            } catch (InterruptedException e) {
                System.err.println("程序被中断");
                break;
            } catch (Exception e) {
                System.err.println("执行出错: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    private static void checkAndNotify() throws Exception {
        String json = fetchReservationData();
        ApiResponse response = null;
        try {
            response = MAPPER.readValue(json, ApiResponse.class);
        } catch (Exception e) {
            System.out.println("解析API响应失败, json: " + json);
            throw e;
        }

        List<Appointment> availableAppointments = response.content.stream()
                .filter(AppointmentNotice260305::isAvailable)
                .toList();

        if (availableAppointments.isEmpty()) {
            System.out.println("未发现可预约号源，继续监控..., time: " + LocalDateTime.now());
        } else {
            sendEmail(availableAppointments);
            System.out.println("发现可预约号源，邮件已发送！,availableAppointments: " + availableAppointments);
        }
    }

    private static boolean isAvailable(Appointment appointment) {
        LocalDate curDate = LocalDate.parse(appointment.curDate, DATE_FORMATTER);
        int remainAmount = Integer.parseInt(appointment.remainAmount);

        boolean inRange = !curDate.isBefore(START_DATE) && !curDate.isAfter(END_DATE);
        boolean hasRemain = remainAmount > 0;

        if (inRange && hasRemain) {
            System.out.println("找到预约：医生=" + appointment.resourceName
                    + " 日期=" + appointment.curDate
                    + " 余号=" + remainAmount);
            return true;
        }
        return false;
    }

    private static String fetchReservationData() throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(API_URL).openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setDoOutput(true);

        try (var os = conn.getOutputStream()) {
            os.write(REQUEST_BODY.getBytes(StandardCharsets.UTF_8));
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }
        return response.toString();
    }

    private static void sendEmail(List<Appointment> appointments) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_SERVER);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(SENDER_EMAIL));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(RECIPIENT_EMAIL));
        message.setSubject("预约提醒 - 有可预约号源");

        StringBuilder body = new StringBuilder("发现可预约号源！\n\n");
        for (Appointment apt : appointments) {
            body.append("医生：").append(apt.resourceName)
                    .append("\n日期：").append(apt.curDate)
                    .append("\n余号：").append(apt.remainAmount)
                    .append("\n机构：").append(apt.orgName)
                    .append("\n地址：").append(apt.orgAddr)
                    .append("\n费用：").append(apt.totAmount).append("元")
                    .append("\n\n");
        }

        message.setText(body.toString(), "UTF-8");

        Transport.send(message);
    }

    @Data
    static class ApiResponse {
        private Header header;
        private List<Appointment> content;
    }

    @Data
    static class Header {
        private String resultCode;
        private String resultMsg;
    }

    @Data
    static class Appointment {
        @JsonProperty("CurDate")
        private String curDate;

        @JsonProperty("ResourceName")
        private String resourceName;

        @JsonProperty("RemainAmount")
        private String remainAmount;

        @JsonProperty("OrgName")
        private String orgName;

        @JsonProperty("OrgAddr")
        private String orgAddr;

        @JsonProperty("TotAmount")
        private String totAmount;

        @JsonProperty("DateTypeName")
        private String dateTypeName;
    }
}

