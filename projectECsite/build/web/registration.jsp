<%-- 
    Document   : registration
    Created on : 2017/09/30, 17:46:02
    Author     : guest1Day
--%>
<%@page import="jums.UserDataBeans"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
        HttpSession hs = request.getSession();
        Boolean reinput = false;
        if(hs.getAttribute("mode")!=null && ( (String)hs.getAttribute("mode") ).equals("reinput")){
            reinput = true;
        }
            UserDataBeans udb = (UserDataBeans)hs.getAttribute("udb");
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/body.css">
        <link rel="stylesheet" type="text/css" href="css/registration.css">
        <title>新規登録</title>
    </head>
    <body class="body">
        <div class="main_container">
        <h1>新規登録</h1>
        <form action="registrationconfirm" method="POST" >
            名前<br>
            <input name="name" type="text" size="30" <% if(reinput){out.print("value="+udb.getName()) ;} %> ><br><br>
            パスワード(先頭大文字、英数字8文字)<br>
            <input name="pass" type="text" size="30" ><br><br>
            メールアドレス<br>
            <input name="mail" type="text" size="30" <% if(reinput){out.print("value="+udb.getMail()) ;} %>><br><br>
            住所<br>
            <input name="address" type="text" size="50" <% if(reinput){out.print("value="+udb.getAddress()) ;} %>><br>
            <input type="hidden" name="ac" value='<%= hs.getAttribute("ac")%>'>
            <input class="cofirmButton" type="submit" value="登録内容の確認">
        </form>
        </div>
        <a href="login.jsp" class="toBack"><b>ログイン画面へ</b></a>
    </body>
</html>
