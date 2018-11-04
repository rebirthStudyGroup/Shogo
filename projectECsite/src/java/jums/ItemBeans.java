/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;
import java.io.Serializable;

/**
 *
 * @author guest1Day
 */
public class ItemBeans implements Serializable {
    private String name;
    private String image;
    private String brand;
    private String price;
    private String reviewRate;
    private String reviewCount;
    private String reviewURL;
    
    public ItemBeans(){
        name="";
        image="";
        brand="";
        price="";
        reviewRate="";
        reviewCount="";
        reviewURL="";
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
            this.brand="";
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
        }
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
    public String getReviewURL(){
        return reviewURL;
    }
    public void setReviewURL(String reviewURL){
        if(reviewURL.trim().length()==0){
            this.reviewURL="";
        }else{
            this.reviewURL = reviewURL;
        }
    } 
    
    
}
