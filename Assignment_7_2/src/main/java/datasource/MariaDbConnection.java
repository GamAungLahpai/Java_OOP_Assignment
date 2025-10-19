package datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDbConnection {
    private static Connection conn = null;

    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            try {
                conn = DriverManager.getConnection(
                        "jdbc:mariadb://localhost:3306/currency_converter?user=appuser&password=password123");
            } catch (SQLException e) {
                System.out.println("Connection failed: " + e.getMessage());
                throw e;
            }
        }
        return conn;
    }

    public static void terminate() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
