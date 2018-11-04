<%-- 
    Document   : updateresult
    Created on : 2017/10/07, 15:44:09
    Author     : guest1Day
--%>
<%@page import="jums.UserDataBeans"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
        UserDataBeans udb = (UserDataBeans)session.getAttribute("loginUser");
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/body.css">
        <link rel="stylesheet" type="text/css" href="css/update.css">
        <title>変更完了画面</title>
    </head>
    <body class="body">
        <div class="main_container">
        <h1>変更が完了しました。</h1>
        <table class="table">
            <tr>
                <th class="column1">名前</th>
                <td class="column2"><%= udb.getName() %></td>
            </tr>
            <tr>
                <th class="column1">パスワード</th>
                <td class="column2"><%= udb.getPass() %></td>
            </tr>
            <tr>
                <th class="column1">住所</th>
                <td class="column2"><%= udb.getAddress() %></td>
            </tr>
            <tr>
                <th class="column1">メール</th>
                <td class="column2"><%= udb.getMail() %></td>
            </tr>
        </table><br>
        </div>
        <div class="button">
                <a href="search" class="nextToSearch">検索画面へ</a><br>
                <a href="mypage" class="nextToMypage">マイページへ</a>
        </div>
        <%
        session.removeAttribute("updateUserData");
        session.removeAttribute("ac");
        session.removeAttribute("check");
        %>
        
    </body>
</html>
