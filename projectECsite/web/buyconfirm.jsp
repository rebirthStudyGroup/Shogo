<%-- 
    Document   : buyconfirm
    Created on : 2017/10/08, 12:10:57
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
        NumberFormat nfCur = NumberFormat.getCurrencyInstance();
        
        int totalAmount=0;
        for(ItemDataDTO cartProduct : cartList){
            totalAmount += cartProduct.getCount() * cartProduct.getPrice();
        }
        //改行用の変数を用意
        int j = 0 ;
        
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/body.css">
        <link rel="stylesheet" type="text/css" href="css/productdisplay.css">
        <link rel="stylesheet" type="text/css" href="css/buyconfirm.css">
        <title>購入確認画面</title>
    </head>
    <body class="body">
        <%if(cartList.size()!=0){%>
            <h1>以下の製品を購入しますか？</h1>
            <h2>合計購入金額：<%= nfCur.format(totalAmount) %></h2>
            <form action="buyamountupdate" method="post">
            <div class="itemWrapper">
            <%for(ItemDataDTO cartProduct : cartList){%>
                <div class="item">
                商品名：<%= cartProduct.getItemCode() %><br>
                        <img class="itemImage" src='<%= cartProduct.getImage() %>' ><br>
                        <div class="itemFooter">
                        個数：<select name=<%="cartID_" + String.valueOf( cartProduct.getCartID() ) %>>
                            <% for(int i=0 ; i < 10 ; i++){ %>
                                <option value=<%=i%> <% if(i == cartProduct.getCount() ){out.print("selected");}%>><%= i==0 ? "削除":i %>
                            <% } %>
                            </select><br>
                        値段：<%= nfCur.format( cartProduct.getCount() * cartProduct.getPrice()) %><br><br>
                        </div>
                        <%if(j!=0 && j%5==0){ %>
                            <br>
                        <% } %>
                </div>
            <% } %>
            </div>
            <input type="hidden" name="name" value="test">
                <input class="button nextToUpdate" type="submit" value="数量を変更する">
            </form>
            <a  href="buyresult" class="button nextToBuy">購入する</a>
        <%}else{%>
            <h1>カートの中身が空です</h1>
        <% } %>
            <a href="search" class="button nextToSearch">検索する</a>
    </body>
    </body>
</html>
