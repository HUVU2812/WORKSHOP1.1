/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class DatabaseConnection {
    private static final String DB_NAME = "SpaConsultationPortal";
    private static final String DB_USER_NAME = "sa";
    private static final String DB_PASSWORD = "12345";
public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=" + DB_NAME;
        return DriverManager.getConnection(url, DB_USER_NAME, DB_PASSWORD);
    }
}
