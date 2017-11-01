/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.sql.*;
import java.sql.SQLException;
import base.DataBase;
import java.lang.StringBuffer;
import java.util.ArrayList;
        
        /**
 *
 * @author guest1Day
 */
public class ItemDataDAO{
    
    public void Insert(int userID,ItemDataDTO idd)throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        final String insertStr = "insert into cart(userID,itemCode,itemCount,itemPrice,image,brand,reviewRate,reviewCount) values(?,?,?,?,?,?,?,?) ";
        try{
          con=DataBase.getConnection();
          ps=con.prepareStatement(insertStr);
          ps.setInt(1, userID);
          ps.setString(2, idd.getItemCode() );
          ps.setInt(3, idd.getCount());//★★アイテム数を初回から複数指定できるように変更を加える余地あり
          ps.setInt(4, idd.getPrice() );
          ps.setString(5, idd.getImage() );
          ps.setString(6, idd.getBrand() );
          ps.setString(7, idd.getReviewRate() );
          ps.setString(8, idd.getReviewCount() );
            
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
    
    //▲buyamountupdate▼
    public void Update(int userID,ItemDataDTO idd) throws SQLException{
     Connection con = null;
     PreparedStatement ps = null;
     final String updateStr = "update cart set itemCount = ? where UserID = ? and cartID = ?";
     try{
         con=DataBase.getConnection();
         ps=con.prepareStatement(updateStr);
         ps.setInt( 1,idd.getCount() );
         ps.setInt( 2, userID);
         ps.setInt( 3,idd.getCartID() );
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
        
        
    //▲inputcart,mypage▼
    public ArrayList<ItemDataDTO> SearchByItemCode(int userID,String itemCode) throws SQLException{
        ArrayList<ItemDataDTO> itemList = new ArrayList<ItemDataDTO>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs =null;
        StringBuffer searchStr = new StringBuffer();
        searchStr.append("select * from cart where userID = ? ");
        try{
            con=DataBase.getConnection();
            //2つ目の引数が空白だった場合、ログインユーザーのカートをすべて表示する
            if(itemCode.equals("")){
                ps=con.prepareStatement(searchStr.toString());
                ps.setInt(1, userID);
            }else{
                searchStr.append("and itemCode = ?");
                ps=con.prepareStatement(searchStr.toString());
                ps.setInt(1, userID);
                ps.setString(2, itemCode);
            }
            
            rs=ps.executeQuery();
            
            while( rs.next() ){
                ItemDataDTO idd = new ItemDataDTO();
                idd.setCartID( rs.getInt("cartID") );
                idd.setItemCode(rs.getString("itemCode") );
                idd.setCount(rs.getInt("ItemCount") );
                idd.setPrice(rs.getInt("ItemPrice") );
                idd.setImage(rs.getString("image") );
                idd.setBrand(rs.getString("brand") );
                idd.setReviewRate(rs.getString("reviewRate") );
                idd.setReviewCount(rs.getString("reviewCount") );
                itemList.add(idd);
            }
            
            return itemList;
            
        }catch(Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }finally{
            if(con!=null){
                con.close();
            }
            if(ps!=null){
                ps.close();
            }
            if(rs != null){
                rs.close();
            }
        }
        
    }

     //▲buyamountupdate▼
    public void Delete(ArrayList<Integer> cartIDs) throws SQLException{
        if(cartIDs.size()!=0){
            Connection con = null;
            PreparedStatement ps = null;
            final String deleteString = "delete from cart where cartID in (";
            StringBuffer deleteStr = new StringBuffer();
            deleteStr.append(deleteString);
            try{
                con=DataBase.getConnection();
                for(int cartID : cartIDs){
                    deleteStr.append(cartID+(cartID==cartIDs.get(cartIDs.size()-1) ? ")":","));
                }

                ps=con.prepareStatement(deleteStr.toString());

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
    
        
    }
   
    

