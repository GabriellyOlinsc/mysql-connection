package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceMySQL {
    public Connection conectaBD() throws SQLException ,ClassNotFoundException {
        //"jdbc:mysql://IPdoBanco:Porta/nomeDoSeuBancoDeDados?useSSL=false&serverTimezone=UTC";

        String url = "jdbc:mysql://68.64.164.97:18627/dbConnection_M3?useSSL=false&serverTimezone=UTC";
        String user = "admin";
        String passwd = "yU4H4jyZ";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, passwd);
        return con;
    }
}
