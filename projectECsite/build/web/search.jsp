<%@page import="jums.Search" 
        import="java.util.LinkedHashMap" 
        import="java.util.Map"
        import="jums.UserDataBeans"%>
<%-- 
    Document   : top
    Created on : 2017/09/16, 18:33:54
    Author     : guest1Day
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
        LinkedHashMap<String,String> category = new LinkedHashMap<String,String>();
        category = (LinkedHashMap<String,String>)request.getAttribute("category");
        LinkedHashMap<String,String> sort = new LinkedHashMap<String,String>();
        sort = (LinkedHashMap<String,String>)request.getAttribute("sort");
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
        <link rel="stylesheet" type="text/css" href="css/body.css">
        <link rel="stylesheet" type="text/css" href="css/search.css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <title>商品を検索する</title>
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
        <div class="main_container">
        <h1>ようこそ日本最大のショッピングサイトへ</h1>
            <div class="search_box"><form action="searchresult" method="get">
                <select name="category_id" style="height: 24px;float:left"><%--カテゴリー選択--%>
                    <% for(Map.Entry<String,String> hs: category.entrySet() ){ %>
                        <option value='<%= hs.getKey() %>'><%= hs.getValue() %></option>
                    <% } %>
                </select> 
                <select name="sort" style="height: 24px;margin-right: 1px;float:left"><%--並び順選択--%>
                    <% for(Map.Entry<String,String> hs: sort.entrySet() ){ %>
                        <option value='<%= hs.getKey() %>'><%= hs.getValue() %></option>
                    <% } %>
                </select> 
                <input type="text" name="query" style="height: 18px;float:left"> 
                <input type="hidden" name="offset" value="0">
                <input type="submit" value="Yahooショッピングで検索">
                </form></div>
        </div>
    </body>
</html>
