<%-- 
    Document   : top
    Created on : 2017/09/16, 19:26:55
    Author     : guest1Day
--%>
<%@page import="jums.UserDataBeans"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
        //ログイン有無の確認
        Boolean loginCheck = false;
        String name ="";
        if( session.getAttribute("loginUser")!=null){
            loginCheck = true;
            UserDataBeans udb = (UserDataBeans)session.getAttribute("loginUser");
            name=udb.getName();
        }     
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/header.css">
        <link rel="stylesheet" type="text/css" href="css/top.css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <title>TOP 画面</title>
    </head>
    <body class="body">
        <div class="header"><%--ヘッド部分--%>
                <p class="header-left"><i class="fa fa-shopping-cart" aria-hidden="true"></i>shopping.com</p><%--awesomeのリンクをDLしておく--%>
            <div class="header-right">
            <% if( loginCheck == true){ %>
                <a href="mypage">アカウント名：<%= name%></a>
            <% }else{ %>
                <a href="login.jsp">ログイン</a>
            <% } %>
            </div>
        </div><%--ヘッド部分--%>
        <div class="body_wrapper"><%--ボディ部分--%>
                <h1>Top画面</h1>
                <a href="search" class="toSearch">検索画面へ</a>
        </div>
    </body>
</html>
