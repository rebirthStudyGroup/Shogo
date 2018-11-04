/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.sql.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import base.DataBase;

/**
 *
 * @author guest1Day
 */
public class UserDataDAO {
    
    
    /**
    *   新規登録内容をデータベースにインプットする
    *
    */
    public void insert(UserDataBeans udb) throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        final String insertStr = "insert into user_t(name,password,mail,address,newDate) values(?,?,?,?,?)";
        con = DataBase.getConnection();
    try{     
        ps=con.prepareStatement(insertStr);
        ps.setString(1,udb.getName());
        ps.setString(2,udb.getPass());
        ps.setString(3,udb.getMail());
        ps.setString(4,udb.getAddress());
        ps.setTimestamp(5,new Timestamp(System.currentTimeMillis()));
        
        ps.executeUpdate();        
        
    }catch(Exception e){
        throw new IllegalArgumentException(e.getMessage());
    }finally{
        if(con!=null){
            con.close();
        }
        ps.close();
    }
}
    
public void Update(UserDataBeans udb) throws SQLException{
    Connection con = null;
    PreparedStatement ps = null;
    final String updateStr = "update user_t set name = ?,password = ?,mail = ?,address = ? ,newDate = ? where userID = ?";
    try {
        con = DataBase.getConnection();
        ps = con.prepareStatement(updateStr);
        ps.setString(1, udb.getName() );
        ps.setString(2, udb.getPass() );
        ps.setString(3, udb.getMail() );
        ps.setString(4, udb.getAddress() );
        ps.setTimestamp(5,new Timestamp(System.currentTimeMillis()));
        ps.setInt(6,udb.getUserID());
        
        ps.executeUpdate();
    
    }catch(Exception e){
        throw new IllegalArgumentException(e.getMessage());
    }finally{
        if(con!=null){
            con.close();
        }
       if(ps!=null){
            ps.close();
        }

    }
    
}    

public void Delete(int UserID) throws SQLException{
    Connection con = null;
    PreparedStatement ps = null;
    final String deleteStr = "delete from user_t where userID = ?";
    
    try{
        con = DataBase.getConnection();
        ps=con.prepareStatement(deleteStr);
        ps.setInt(1, UserID);
        ps.executeUpdate();
    }catch(Exception e){
        throw new IllegalArgumentException(e.getMessage() );
    }finally{
        if(con!=null){
            con.close();
        }
        if(ps!=null){
            ps.close();
        }
    }
    
    
}

    
/*
*  登録ユーザーを検索する(検索条件：名前)    
*/
public UserDataBeans searchByMail(String mail) throws SQLException{
    UserDataBeans udb = new UserDataBeans();
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    con = DataBase.getConnection();
    ps=con.prepareStatement("select * from user_t where mail = ?");
    ps.setString(1,mail);
    
    rs=ps.executeQuery();    
    while(rs.next()){
        udb.setUserID( rs.getInt(1) );
        udb.setName( rs.getString("name") );
        udb.setPass( rs.getString("password") );
        udb.setMail( rs.getString("mail") );
        udb.setAddress( rs.getString("address") );
    }
    
    if(con!=null){
        con.close();
    }
    if(ps!=null){
        ps.close();
    }
    if(rs!=null){
        rs.close();
    }
    return udb;
}

public UserDataBeans searchByUserID(int userID) throws SQLException{
    UserDataBeans udb = new UserDataBeans();
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    con = DataBase.getConnection();
    ps=con.prepareStatement("select * from user_t where userID = ?");
    ps.setInt(1,userID);
    
    rs=ps.executeQuery();    
    while(rs.next()){
        udb.setUserID( rs.getInt(1) );
        udb.setName( rs.getString("name") );
        udb.setPass( rs.getString("password") );
        udb.setMail( rs.getString("mail") );
        udb.setAddress( rs.getString("address") );
    }
    
    if(con!=null){
        con.close();
    }
    if(ps!=null){
        ps.close();
    }
    if(rs!=null){
        rs.close();
    }
    return udb;
}

    
    
}
