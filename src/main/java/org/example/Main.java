package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class Main {
    public static void main(String[] args) {
        try{
            System.out.println("ola");
            DataSourceMySQL ds = new DataSourceMySQL();
            Connection c = ds.conectaBD();
            System.out.println(c);
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Pagador");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getString("nomeCompleto"));
                System.out.println(resultSet.getString("email"));
                System.out.println(resultSet.getString("numeroDocumento"));
                System.out.println(resultSet.getString("telefone"));
                System.out.println();
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

}