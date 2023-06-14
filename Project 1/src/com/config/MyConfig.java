package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConfig {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/produk";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    protected static Connection connect;
    protected static Statement statement;
    protected static ResultSet resultSet;
    protected static String query;

    // public MyConfig() {
    //     connection();
    // }

    public static void connection() {
        try {
            connect = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("=".repeat(40));
            System.out.println("Data Base Connected Succes");
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
