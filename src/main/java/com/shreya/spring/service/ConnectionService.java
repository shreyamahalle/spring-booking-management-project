package com.shreya.spring.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionService {

    public static Connection getConnection() {
        Connection connection = null;
        try {
            String url = "jdbc:mysql://localhost:3306/foodorder";
            String username = "root";
            String password = "root";
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");
        } catch (SQLException e) {

            System.err.println("Connection error: " + e.getMessage());
        }
        return connection;
    }
}