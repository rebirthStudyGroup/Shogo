<%-- 
    Document   : buyconfirm
    Created on : 2017/10/07, 17:23:13
    Author     : guest1Day
--%>
<%@page import="jums.ItemDataDTO"
        import="java.util.ArrayList"
        import="java.text.NumberFormat"
         %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
         ArrayList<ItemDataDTO> cartList = new ArrayList<ItemDataDTO>();
         cartList = (ArrayList<ItemDataDTO>)request.getAttribute("cartList");
         ItemDataDTO addedProduct = (ItemDataDTO)request.getAttribute("addedProduct");
         NumberFormat nfCur = NumberFormat.getCurrencyInstance();
         String totalPrice = nfCur.format( addedProduct.getCount() * addedProduct.getPrice() ) ;
         int itemAmount=cartList.size();
         
        //カートリストを5つ毎に段落で区切るための変数
        int i = 0;
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>カートの中身確認画面</title>
        <link rel="stylesheet" type="text/css" href="css/body.css">
        <link rel="stylesheet" type="text/css" href="css/inputcart.css">
        
    </head>
    <body class="body">
        <div class="inputItemBox">
            <h1>以下の商品をカートに入れました</h1>
            <div class="inputItem">
            商品名：<%= addedProduct.getItemCode() %><br>
                    <img src='<%= addedProduct.getImage() %>' class="itemImage"><br>
            <div class="itemFooter">
                個数：<%= addedProduct.getCount() %><br>
                値段：<%= totalPrice %><br>
            </div>
            </div>
        </div>
            <br>
        <div class="button">
            <a href="buyconfirm" class="nextToBuy">購入画面へ</a>
            <a href="search" class="nextToSearch">検索画面へ</a>
        <br>
        </div>
        <h1 class="cartList">その他カート内の商品</h1>
        <div class="itemWrapper"    >
                <%for(ItemDataDTO cartProduct : cartList){ %><%--forThird--%>
                <div class="item">
                    <img src='<%= cartProduct.getImage() %>' class="itemImage"><br>
                    商品名：<%= cartProduct.getItemCode() %><br>
                    <div class="itemFooter">
                        個数：<%= cartProduct.getCount() %><br>
                        値段：<%= nfCur.format( cartProduct.getCount() * cartProduct.getPrice()) %><br>
                    </div>
                </div>
                <% } %><%--forThird--%>
            <br></div>
    </body>
</html>
