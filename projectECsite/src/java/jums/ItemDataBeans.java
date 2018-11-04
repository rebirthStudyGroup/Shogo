/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;
import java.io.Serializable;
import java.text.NumberFormat;

/**
 *
 * @author guest1Day
 */
public class ItemDataBeans implements Serializable {
    private String name;
    private String image;
    private String brand;
    private String price;
    private String reviewRate;
    private String reviewCount;
    private String reviewPer;
    NumberFormat nfPer = NumberFormat.getPercentInstance();
    
    public ItemDataBeans(){
        name="";
        image="";
        brand="";
        price="";
        reviewRate="";
        reviewCount="";
        reviewPer="";
    }
    
    public String getName(){
        return name;
    }
    public void setName(String name){
        if(name.trim().length()==0){
            this.name="";
        }else{
            this.name = name;
        }
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
    public String getPrice(){
        return price;
    }
    public void setPrice(String price){
        if(price.trim().length()==0){
            this.price="";
        }else{
            this.price = price;
        }
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

    
    public void UDDMapping(ItemDataDTO idd){
        idd.setItemCode(this.name);
        idd.setImage(this.image);
        idd.setBrand(this.brand);
        idd.setPrice( Integer.parseInt( this.price ));
        idd.setReviewRate(this.reviewRate);
        idd.setReviewCount(this.reviewCount);
    }
    
    
}
