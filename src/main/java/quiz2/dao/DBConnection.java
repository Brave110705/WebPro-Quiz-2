package quiz2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;

public class DBConnection {

    public static Connection getConnection() {
        try {
            Properties props = new Properties();
            InputStream is = DBConnection.class.getClassLoader().getResourceAsStream("jdbc.properties");
            if (is != null) {
                props.load(is);
            }

            String envUrl  = System.getenv("MYSQL_URL");
            String envUser = System.getenv("MYSQL_USER");
            String envPass = System.getenv("MYSQL_PASSWORD");

            String url, username, password;

            if (envUrl != null && envUser != null && envPass != null) {
                url = envUrl + "?useSSL=false&allowPublicKeyRetrieval=true";
                username = envUser;
                password = envPass;
            } else {
                String driver = props.getProperty("jdbc.driver");
                Class.forName(driver);

                url = props.getProperty("jdbc.url");
                username = props.getProperty("jdbc.username");
                password = props.getProperty("jdbc.password");
            }

            return DriverManager.getConnection(url, username, password);

        } catch (Exception e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}
