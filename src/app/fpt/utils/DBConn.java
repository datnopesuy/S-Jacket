package app.fpt.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConn {
    
    public static String USER = "sa";
    public static String PASS = "trangtrang";
    public static String URL = "jdbc:sqlserver://localhost:1433;databaseName=DUAN1;trustServerCertificate=true";
    static{
        try {
            Class.forName ("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public static Connection getConnection(){
        Connection cn = null;
        try {
            cn = DriverManager.getConnection(URL,USER,PASS);
        } catch (SQLException ex) {
            Logger.getLogger(DBConn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cn;
    } 
    public static void main(String[] args) {
        Connection cn = getConnection();
        if(cn != null){
            System.out.println("thanh cong");
        }else{
            System.out.println("that bai");
        }
    }
}
