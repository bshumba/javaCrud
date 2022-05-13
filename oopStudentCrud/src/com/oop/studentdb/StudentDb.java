
package com.oop.studentdb;

import java.sql.*;

public class StudentDb {
    
    static Connection con;
    static String url = "jdbc:mysql://localhost/java_prac";
    static String uname = "root";
    static String pass = "";
    
    public static Connection getConnection() throws Exception {
        if(con == null) {
            con = DriverManager.getConnection(url,uname,pass);
        }
        return con;
    }
}
