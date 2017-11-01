/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

/**
 *
 * @author guest1Day
 */
public class UserDataBeans {
    private String name;
    private String pass;
    private String address;
    private String mail;
    private int userID;
    
    UserDataBeans(){
        name="";
        pass="";
        address="";
        mail="";
        userID=0;
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
        public String getPass(){
        return pass;
    }
    public void setPass(String pass){
        if(pass.trim().length()==0){
            this.pass="";
        }else{
            this.pass = pass;
        }
    } 
        public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        if(address.trim().length()==0){
            this.address="";
        }else{
            this.address = address;
        }
    } 
        public String getMail(){
        return mail;
    }
    public void setMail(String mail){
        if(mail.trim().length()==0){
            this.mail="";
        }else{
            this.mail = mail;
        }
    } 
        public int getUserID(){
        return userID;
    }
    public void setUserID(int userID){
            this.userID = userID;
    } 

    
    /*
    * 新規登録画面で入力した内容に不備がないかを確認
    */
    public String check(){
        String check="";
        
        if(this.name==""){ check+="【名前】の入力が不完全です<br>";  }
        if(this.pass=="" || this.pass.matches("[A-Z][A-Za-z0-9]{8}"))
            { check+="【パスワード】の入力が不完全です<br>※先頭が大文字である事、英数字で8文字である事を確認してください。<br>";  }
        if(this.address==""){check+="【住所】の入力が不完全です。<br>"; }
        if( this.mail=="" || !this.mail.contains("@") )
            {check+="【メールアドレス】の入力が不完全です。";}
        return check;
    }
    
}
