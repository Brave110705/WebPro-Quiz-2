// package quiz2.dao;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.util.Properties;
// import java.io.InputStream;

// public class DBConnection {

//     public static Connection getConnection() {
//         try {
//             Properties props = new Properties();
//             InputStream is = DBConnection.class.getClassLoader().getResourceAsStream("jdbc.properties");
//             if (is != null) {
//                 props.load(is);
//             }

//             String envUrl  = System.getenv("MYSQL_URL");
//             String envUser = System.getenv("MYSQL_USER");
//             String envPass = System.getenv("MYSQL_PASSWORD");

//             String url, username, password;

//             if (envUrl != null && envUser != null && envPass != null) {
//                 url = envUrl + "?useSSL=false&allowPublicKeyRetrieval=true";
//                 username = envUser;
//                 password = envPass;
//             } else {
//                 String driver = props.getProperty("jdbc.driver");
//                 Class.forName(driver);

//                 url = props.getProperty("jdbc.url");
//                 username = props.getProperty("jdbc.username");
//                 password = props.getProperty("jdbc.password");
//             }

//             return DriverManager.getConnection(url, username, password);

//         } catch (Exception e) {
//             throw new RuntimeException("Error connecting to the database", e);
//         }
//     }
// }

package quiz2.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        try {
            // Get environment variables from Zeabur
            String host = System.getenv("MYSQL_HOST");
            String port = System.getenv("MYSQL_PORT");
            String db   = System.getenv("MYSQL_DATABASE");
            String user = System.getenv("MYSQL_USERNAME");
            String pass = System.getenv("MYSQL_PASSWORD");

            // If running locally (no env vars), fallback to jdbc.properties
            if (host == null || port == null || db == null || user == null || pass == null) {
                return getLocalConnection();
            }

            String url = "jdbc:mysql://" + host + ":" + port + "/" + db +
                    "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    private static Connection getLocalConnection() {
        try {
            java.util.Properties props = new java.util.Properties();
            java.io.InputStream is = DBConnection.class.getClassLoader().getResourceAsStream("jdbc.properties");
            props.load(is);

            String driver = props.getProperty("jdbc.driver");
            Class.forName(driver);

            String url = props.getProperty("jdbc.url");
            String username = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");

            return DriverManager.getConnection(url, username, password);

        } catch (Exception e) {
            throw new RuntimeException("Error loading local JDBC config", e);
        }
    }
}
