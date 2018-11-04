<%-- 
    Document   : error
    Created on : 2017/09/17, 12:42:37
    Author     : guest1Day
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>エラー画面</h1>
        <p>エラーが発生しました。以下の内容をご確認ください。</p><br>
        <%= (String)request.getAttribute("error") %><br><br>
        <a href="top.jsp">Top画面へ戻る</a>
        <a href="search">検索画面へ戻る</a>
    </body>
</html>
