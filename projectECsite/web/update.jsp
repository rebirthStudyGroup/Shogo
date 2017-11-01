<%-- 
    Document   : update
    Created on : 2017/10/07, 14:49:50
    Author     : guest1Day
--%>
<%@page import="jums.UserDataBeans"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
        UserDataBeans udb = (UserDataBeans)session.getAttribute("loginUser");
        String ac = String.valueOf( session.getAttribute("ac") );
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/body.css">
        <link rel="stylesheet" type="text/css" href="css/registration.css">
        <title>登録内容変更画面</title>
    </head>
    <body class="body">
        <div class="main_container">
        <h1>登録変更内容</h1>
        <form action="updateconfirm" type="POST" >
            名前<br>
            <input type="text" name="name" value='<%= udb.getName() %>' ><br>
            メールアドレス<br>
            <input type="text" name="mail" value='<%= udb.getMail() %>'><br>
            住所<br>
            <input type="text" name="address" value='<%= udb.getAddress() %>'><br>
            パスワード<br>
            <input type="text" name="pass" value='<%= udb.getPass() %>'><br>
            <input type="hidden" name="ac" value=<%= ac%>>
            <input type="hidden" name="userID" value=<%= udb.getUserID() %>>
        <input type="submit" value="変更">
        </form>
        </div>
    </body>
</html>
