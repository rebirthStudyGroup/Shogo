<%-- 
    Document   : loginresult
    Created on : 2017/10/01, 13:28:24
    Author     : guest1Day
--%>

<%@page import="jums.UserDataBeans"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
        String loginError = (String)request.getAttribute("loginError");
        UserDataBeans udb ;
        if(loginError.equals("")){
            udb = (UserDataBeans)session.getAttribute("loginUser");
        }else{
            udb = null;
        }
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/body.css">
        <link rel="stylesheet" type="text/css" href="css/loginresult.css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <title>ログイン確認画面</title>
    </head>
    <body class="body">
        <% if(loginError.equals("")){ %>
        <div class="loginResult">
            <b><%= udb.getName()%></b>さんのログインに成功しました。<br>
            引き続き、ショッピングをお楽しみください。<br>
        </div>
            <i class="fa fa-arrow-down" aria-hidden="true"></i><br>
            <i class="fa fa-arrow-down" aria-hidden="true"></i><br>
            <i class="fa fa-arrow-down" aria-hidden="true"></i><br>
            <a href="search" class="toNext"><b>検索画面へ</b></a><br>
            <a href="mypage" class="toNext"><b>マイページへ</b></a>
            <%
            session.removeAttribute("ac");
            %>
        <% }else{ %>
        <div class="loginResult">
            ログインに失敗しました。以下のエラー内容をご確認下さい。<br>
            <b><%= loginError%></b><br>
        </div>
            <i class="fa fa-arrow-down" aria-hidden="true"></i><br>
            <i class="fa fa-arrow-down" aria-hidden="true"></i><br>
            <i class="fa fa-arrow-down" aria-hidden="true"></i><br>
            <a href="login.jsp" class="toNext"><b>ログイン画面へ</b></a>
        <% }%>
        
    </body>
</html>
