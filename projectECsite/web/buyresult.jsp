<%-- 
    Document   : buyresult
    Created on : 2017/10/08, 14:41:39
    Author     : guest1Day
--%>
<%@page import="jums.ItemDataDTO"
        import="java.util.ArrayList"
        import="java.text.NumberFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
        ArrayList<ItemDataDTO> boughtList = (ArrayList<ItemDataDTO>)request.getAttribute("buyingList");
        NumberFormat nfCur = NumberFormat.getCurrencyInstance();
        String now = (String)request.getAttribute("now");
        
        int totalAmount=0;
        for(ItemDataDTO cartProduct : boughtList){
            totalAmount += cartProduct.getCount() * cartProduct.getPrice();
        }
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/body.css">
        <link rel="stylesheet" type="text/css" href="css/productdisplay.css">
        <link rel="stylesheet" type="text/css" href="css/buyresult.css">
        <title>購入完了画面</title>
    </head>
    <body class="body">
        <h1>以下の製品を購入しました (日時：<%= now%>)</h1>
        <p>購入金額：<%= nfCur.format(totalAmount)%></p>
        <div class="itemWrapper">
        <%for(ItemDataDTO cartProduct : boughtList){%>
            <div class="item">
            <img  class="itemImage" src='<%= cartProduct.getImage() %>' ><br>
            商品名：<%= cartProduct.getItemCode() %><br>
            <div class="itemFooter">
                個数：<%= cartProduct.getCount() %><br>
                値段：<%= nfCur.format( cartProduct.getCount() * cartProduct.getPrice()) %><br>
            </div>
            </div>            
        <% } %>
        </div>
        <a href="search" class="nextToSearch">検索画面へ</a>
    </body>
</html>
