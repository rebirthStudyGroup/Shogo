/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.sql.*;

/**
 *
 * @author guest1Day
 */
public class DataBase {
    
    public static Connection getConnection(){
        Connection con = null;
    try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ECsite_db","shogo","hayashi9999");
        return con;        
    }catch(SQLException e){
        throw new IllegalArgumentException(e.getMessage());
    }catch(ClassNotFoundException e){
        throw new IllegalMonitorStateException(e.getMessage());
    }catch(Exception e){
        throw new IllegalArgumentException(e.getMessage());
    }
        
        
    }
    
    
}
