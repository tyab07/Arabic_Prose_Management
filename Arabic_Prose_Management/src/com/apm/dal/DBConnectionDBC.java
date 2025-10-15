package com.apm.dal;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionDBC {

    private static Connection connection = null;

    private static final String URL = "jdbc:mysql://localhost:3306/Arabic_Prose_Management?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "tayyab15";

    // Private constructor — prevents instantiation
    private DBConnectionDBC() {}

    // Singleton method to get connection
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Database connected successfully (shared connection)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Optional: method to close connection gracefully
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("🔒 Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
