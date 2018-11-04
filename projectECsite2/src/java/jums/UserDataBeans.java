/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author shogo
 */
public class UserDataBeans implements Serializable {
    private String name="";
    private String mail="";
    private String address="";
    private Date birthday=null;
    
    UserDataBeans(){
        
    }
   
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
    
}
