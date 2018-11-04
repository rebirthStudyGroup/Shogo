<%-- 
    Document   : registrationresult
    Created on : 2017/10/01, 11:58:53
    Author     : guest1Day
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jums.UserDataBeans" %>
<!DOCTYPE html>
<html>
    <head>
        <%
        HttpSession hs = request.getSession();
        UserDataBeans udb = (UserDataBeans)hs.getAttribute("udb");
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/body.css">
        <link rel="stylesheet" type="text/css" href="css/registration.css">
        <title>登録完了画面</title>
    </head>
    <body class="body">
        <div class="main_container">
        <h1>登録結果</h1>
        名前：<b><%= udb.getName() %></b><br>
        パスワード：<b><%= udb.getPass() %></b><br>
        住所：<b><%= udb.getAddress() %></b><br>
        メール：<b><%= udb.getMail() %></b><br><br>
        上記の内容で登録しました。<br>
        </div>
        <% hs.invalidate();  %>
        <a href="top.jsp" class="toBack">Top画面へ戻る</a>
    </body>
</html>
