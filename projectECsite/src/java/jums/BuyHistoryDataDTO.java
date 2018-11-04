/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.sql.Timestamp;

/**
 *
 * @author guest1Day
 */
public class BuyHistoryDataDTO {
    private int buyID;
    private String itemCode;
    private String image;
    private String brand;
    private int count;
    private int price;
    private Timestamp newDate;
    
    public BuyHistoryDataDTO(){
        buyID=0;
        itemCode="";
        image="";
        brand="";
        count=0;
        price=0;
        newDate=null;
    }
    
    public String getItemCode(){
        return itemCode;
    }
    public void setItemCode(String itemCode){
        if(itemCode.trim().length()==0){
            this.itemCode="";
        }else{
            this.itemCode = itemCode;
        }
    } 
    public int getBuyID(){
        return buyID;
    }
    public void setBuyID(int buyID){
            this.buyID = buyID;
    } 
    public String getImage(){
        return image;
    }
    public void setImage(String image){
        if(image.trim().length()==0){
            this.image="";
        }else{
            this.image = image;
        }
    } 
    public int getPrice(){
        return price;
    }
    public void setPrice(int price){
            this.price = price;
    } 
    
    public int getCount(){
        return count;
    }
    public void setCount(int count){
            this.count = count;
    } 
    
    public String getBrand(){
        return brand;
    }
    public void setBrand(String brand){
        if(brand.trim().length()==0){
            this.brand="";
        }else{
            this.brand = brand;
        }
    } 
 
    public Timestamp getNewDate(){
        return newDate;
    }
    public void setNewDate(Timestamp newDate){
            this.newDate = newDate;
    }     
    
}
