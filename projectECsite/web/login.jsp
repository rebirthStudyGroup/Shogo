<%-- 
    Document   : login
    Created on : 2017/09/30, 17:29:19
    Author     : guest1Day
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/body.css">
        <link rel="stylesheet" type="text/css" href="css/login.css">
        <title>JSP Page</title>
    </head>
    <body class="body">
        <div class="main_container">
            <div class="login">
            <form action="loginresult" method="POST">
            <h1>ログイン画面</h1>
            メールアドレス<br>
            <input type="text" name="user_mail" size="30"><br><br>
            パスワード<br>
            <input type="password" name="user_pass" size="30" maxlength="8"><br><br>
                <input type="submit" value="ログイン">
            </form><br>
            </div>
            <div class="regist">
            未登録の方は新規登録画面へお進みください。<br>           
            <a href="registration"><b>新規登録</b></a>
            </div>
        </div>
    </body>
</html>
