/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;
import java.text.NumberFormat;
/**
 *
 * @author guest1Day
 */
public class ItemDataDTO {
    private int cartID;
    private String itemCode;
    private String image;
    private String brand;
    private int count;
    private int price;
    private String reviewRate;
    private String reviewCount;
    private String reviewPer;
    NumberFormat nfPer = NumberFormat.getPercentInstance();
    
    public ItemDataDTO(){
        cartID=0;
        itemCode="";
        image="";
        brand="";
        count=0;
        price=0;
        reviewRate="";
        reviewCount="";
        reviewPer="";
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
    public int getCartID(){
        return cartID;
    }
    public void setCartID(int cartID){
            this.cartID = cartID;
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
            this.brand="-";
        }else{
            this.brand = brand;
        }
    } 
    public String getReviewRate(){
        return reviewRate;
    }
    public void setReviewRate(String reviewRate){
        if(reviewRate.trim().length()==0){
            this.reviewRate="";
        }else{
            this.reviewRate = reviewRate;
            this.reviewPer = nfPer.format( Double.parseDouble(reviewRate) / 5.00 );
        }
    } 
    
    public String getReviewPer(){
        return reviewPer;
    }
    
    public String getReviewCount(){
        return reviewCount;
    }
    public void setReviewCount(String reviewCount){
        if(reviewCount.trim().length()==0){
            this.reviewCount="";
        }else{
            this.reviewCount = reviewCount;
        }
    } 

    
    
}
