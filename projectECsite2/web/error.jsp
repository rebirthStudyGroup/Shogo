<%-- 
    Document   : error
    Created on : 2018/01/08, 23:34:28
    Author     : shogo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
        String name = (String)request.getAttribute("error");
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>エラー画面</title>
    </head>
    <body>
        <h1>エラー画面</h1>
        <p>以下のエラーが発生しました。</p>
        <%= name%>
    </body>
</html>
