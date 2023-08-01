package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String DB_ID = "user0112";
    private static final String DB_PW = "user1234";

    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            // Step 1: Load the JDBC driver
            Class.forName(DRIVER);

            // Step 2: Establish the database connection
            conn = DriverManager.getConnection(URL, DB_ID, DB_PW);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
