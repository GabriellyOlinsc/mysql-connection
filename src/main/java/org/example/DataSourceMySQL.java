package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceMySQL {
    public Connection conectaBD() throws SQLException ,ClassNotFoundException {
        String url = "jdbc:mysql://209.209.40.91:19860/dbConnection_M3?useSSL=false&serverTimezone=UTC";
        String user = "admin";
        String password = "w77LFoqf";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, password);
        return con;
    }
}
