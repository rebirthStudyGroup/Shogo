/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.sql.*;
import java.sql.SQLException;
import base.DataBase;
import java.util.ArrayList;

/**
 *
 * @author guest1Day
 */
public class BuyHistoryDataDAO {
 
    public void Insert(int userID,ItemDataDTO idd) throws SQLException{
         Connection con = null;
        PreparedStatement ps = null;
        final String insertStr = "insert into buy(userID,itemCode,itemCount,itemPrice,image,brand,newDate) values(?,?,?,?,?,?,?) ";
        try{
          con=DataBase.getConnection();
          ps=con.prepareStatement(insertStr);
          ps.setInt(1, userID);
          ps.setString(2, idd.getItemCode() );
          ps.setInt(3, idd.getCount());//★★アイテム数を初回から複数指定できるように変更を加える余地あり
          ps.setInt(4, idd.getPrice() );
          ps.setString(5, idd.getImage() );
          ps.setString(6, idd.getBrand() );
          ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            
          ps.executeUpdate();
         
        }catch(SQLException e ){
            throw new IllegalArgumentException(e.getMessage());
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
    
    public ArrayList<BuyHistoryDataDTO> SearchByUserID(int userID) throws SQLException{
        ArrayList<BuyHistoryDataDTO> boughtList = new ArrayList<BuyHistoryDataDTO>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        final String searchStr = "select * from buy where userID = ?";
        
        try{
            con=DataBase.getConnection();
            
            ps=con.prepareStatement(searchStr);
            ps.setInt(1,userID);
            
            rs=ps.executeQuery();
            
            while(rs.next()){
                BuyHistoryDataDTO bhdd = new BuyHistoryDataDTO();
                bhdd.setBuyID( rs.getInt("buyID") );
                bhdd.setItemCode(rs.getString("itemCode") );
                bhdd.setCount(rs.getInt("ItemCount") );
                bhdd.setPrice(rs.getInt("ItemPrice") );
                bhdd.setImage(rs.getString("image") );
                bhdd.setBrand(rs.getString("brand") );
                bhdd.setNewDate(rs.getTimestamp("newDate") );
                boughtList.add(bhdd);
            }
            
            return boughtList;
            
        }catch(Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }finally{
            if(con!=null){
                con.close();
            }
            if(ps!=null){
                ps.close();
            }
            if(rs!=null){
                rs.close();
            }
        }
        
        
    }
    
    public void Delete(int UserID) throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        final String deleteStr = "delete from buy where userID = ?";
        
        try{
            con = DataBase.getConnection();
            
            ps = con.prepareStatement(deleteStr);
            ps.setInt(1, UserID);
            
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
    
}
