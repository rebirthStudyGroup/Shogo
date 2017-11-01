<%-- 
    Document   : updateconfirm
    Created on : 2017/10/07, 15:22:40
    Author     : guest1Day
--%>
<%@page import="jums.UserDataBeans"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
        UserDataBeans udb = (UserDataBeans)session.getAttribute("updateUserData");
        String check = (String)request.getAttribute("check");
        String ac = String.valueOf( session.getAttribute("ac") );
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/body.css">
        <link rel="stylesheet" type="text/css" href="css/update.css">
        <title>変更内容確認画面</title>
    </head>
    <body class="body">
        <% if( check.equals("") ){ %>
        <div class="main_container">
            <form action="updateresult">
                <h1>登録変更内容の確認</h1>
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
                上記の内容で登録します。よろしいですか？<br>
                <input type="hidden" name="ac" value=<%= ac%> >
                <input type="submit" value="はい">
            </form>
        </div>
            <a href="update" class="nextToUpdate">変更画面へ</a><br>
        <% }else{ %>
            エラーが発生しました。以下のエラー内容をご確認ください。<br>
            <p><%= check%></p>
        <% } %>
            <a href="mypage" class="nextToMypage">マイぺージへ</a>
    </body>
</html>
