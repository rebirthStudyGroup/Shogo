<%-- 
    Document   : loginTop
    Created on : 2018/01/08, 23:12:11
    Author     : shogo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ログイン画面</title>
    </head>
    <body>
        <h1>ログイン画面</h1>
        <form action="Mypage" method="GET">
            <input type="text" name="name">
            <input type="text" name="mail">
            <input type="text" name="address">
            <input type="text" name="birthday">
            <input type="submit" value="決定">
        </form>
        <a href="registerTop.jsp">新規登録</a>
    </body>
</html>
