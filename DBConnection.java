package com.example.studentdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // TODO: Update these with your local MySQL credentials
    private static final String URL = "jdbc:mysql://localhost:3306/student_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Naufal@100";

    public static Connection getConnection() throws SQLException {
        // Optional for older drivers: uncomment the next line
        // try { Class.forName("com.mysql.cj.jdbc.Driver"); } catch (ClassNotFoundException ignored) {}
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
