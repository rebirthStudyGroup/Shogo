<%-- 
    Document   : registrationconfirm
    Created on : 2017/09/30, 18:27:16
    Author     : guest1Day
--%>
<%@page import="jums.UserDataBeans"
        
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
        HttpSession hs = request.getSession();
        UserDataBeans udb = (UserDataBeans)hs.getAttribute("udb");
        String check = (String)request.getAttribute("check");
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/body.css">
        <link rel="stylesheet" type="text/css" href="css/registration.css">
        <title>新規登録 確認画面</title>
    </head>
    <body class="body">
            <% if( check.equals("") ){ %>
            <div class="main_container">
                <form action="registrationresult">
                    <h1>新規登録内容の確認</h1>
                    名前：<b><%= udb.getName() %></b><br>
                    パスワード：<b><%= udb.getPass() %></b><br>
                    住所：<b><%= udb.getAddress() %></b><br>
                    メール：<b><%= udb.getMail() %></b><br><br>
                    上記の内容で登録します。よろしいですか？<br>
                    <input type="submit" value="はい">
                </form>
            </div>
                    <a href="registration" class="toBack">戻る</a>
            <% }else{ %>
            <div class="main_container">
                エラーが発生しました。以下のエラー内容をご確認ください。<br>
                <p><b><%= check%></b></p>
            </div>
            <a href="registration" class="toBack">戻る</a>
            <% } %>
    </body>
</html>
