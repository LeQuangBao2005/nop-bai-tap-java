/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author ADMIN
 */
public class DatabaseUtil {
    public static  Connection getConnection()
    {
        Connection conn=null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             conn = (Connection) DriverManager.getConnection("jdbc:sqlserver://pc341;databaseName=demodb", "sa", "sa");
        }catch (Exception e){
            System.out.println("Loi:" + e.toString());
        }
        return conn;
    }
}

