<%-- 
    Document   : buyhistory
    Created on : 2017/10/08, 14:57:50
    Author     : guest1Day
--%>

<%@page import="java.text.NumberFormat"%>
<%@page import="jums.BuyHistoryDataDTO"
        import="java.util.ArrayList"
        import="java.text.NumberFormat"
        import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
        ArrayList<BuyHistoryDataDTO> buyHis = new ArrayList<BuyHistoryDataDTO>();
        buyHis = (ArrayList<BuyHistoryDataDTO>)request.getAttribute("buyHis");
        NumberFormat nfCur = NumberFormat.getCurrencyInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/body.css">
        <link rel="stylesheet" type="text/css" href="css/buyhistory.css">
        <link rel="stylesheet" type="text/css" href="css/productdisplay.css">
        <title>購入履歴</title>
    </head>
    <body class="body">
        <h1>購入履歴</h1>
        <div class="itemWrapper">
        <%for(BuyHistoryDataDTO boughtProduct : buyHis){%>
        <div class="item">
            購入日：<%= sdf.format(boughtProduct.getNewDate()) %><br>
            商品名：<%= boughtProduct.getItemCode() %><br>
                    <img class="itemImage" src='<%= boughtProduct.getImage() %>' ><br>
            <div class="itemFooter">
            個数：<%= boughtProduct.getCount() %><br>
            値段：<%= nfCur.format( boughtProduct.getCount() * boughtProduct.getPrice()) %><br>            
            </div>
        </div>
        <% } %>
        </div>
        <div class="button">
        <a href="search" class="nextToSearch">検索画面へ戻る</a>
        <a href="mypage" class="nextToMypage">マイページへ戻る</a>
        </div>
    </body>
</html>
