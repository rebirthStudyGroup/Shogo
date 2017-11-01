<%-- 
    Document   : delete
    Created on : 2017/10/07, 16:46:52
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
        <link rel="stylesheet" type="text/css" href="css/delete.css">
        <title>ユーザーデータ削除画面</title>
    </head>
    <body class="body">
        <div class="userTable">
        <h1>削除内容</h1>
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
        </table><br >
        本当に削除してもよろしいですか？
        </div>
        <div class="button">
            <a class="nextToDelete" href="deleteresult">はい</a>
            <a class="nextToMypage" href="mypage">いいえ</a>
        </div>
    </body>
</html>
