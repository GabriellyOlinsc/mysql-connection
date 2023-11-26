package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceMySQL {
    public Connection conectaBD() throws SQLException ,ClassNotFoundException {
        //"jdbc:mysql://IPdoBanco:Porta/nomeDoSeuBancoDeDados?useSSL=false&serverTimezone=UTC";

        String url = "jdbc:mysql://209.209.40.91:19860/dbConnection_M3?useSSL=false&serverTimezone=UTC";
        String user = "admin";   //seu username
        String passwd = "w77LFoqf";  //sua senha
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, passwd);
        return con;
    }
}
