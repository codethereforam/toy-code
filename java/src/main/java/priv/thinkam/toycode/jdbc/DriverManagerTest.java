package priv.thinkam.toycode.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author thinkam
 * @date 2018/03/26
 */
public class DriverManagerTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        final String url = "jdbc:mysql://localhost/test?useSSL=false&serverTimezone=GMT%2B8&useUnicode=true" +
                "&characterEncoding=utf8";
        final String username = "root";
        final String password = "978299";
        Connection conn = DriverManager.getConnection(url, username, password);
    }

}
