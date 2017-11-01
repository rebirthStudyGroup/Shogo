<%-- 
    Document   : mypage
    Created on : 2017/10/07, 13:40:54
    Author     : guest1Day
--%>

<%@page import="jums.UserDataBeans"
        import="java.util.ArrayList"
        import="jums.ItemDataDTO"
        import="java.text.NumberFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
        UserDataBeans udb = (UserDataBeans)session.getAttribute("loginUser");
        String ac = String.valueOf( session.getAttribute("ac") );
        
        ArrayList<ItemDataDTO> cartList = new ArrayList<ItemDataDTO>();
        cartList = (ArrayList<ItemDataDTO>)request.getAttribute("cartList");
        NumberFormat nfCur = NumberFormat.getCurrencyInstance();
        
        //商品表示の繰り返し回数を決めるint変数を２つ用意
        int first = 0;
        int second = 0;
        if(cartList!=null){
            first = ( cartList.size() ) / 5 + 1 ;
            second = cartList.size() % 5;
        }
        //合計金額を計算する変数
        int totalAmount = 0;
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/body.css">
        <link rel="stylesheet" type="text/css" href="css/mypage.css">
        <link rel="stylesheet" type="text/css" href="css/productdisplay.css">
        <title>マイページ</title>
    </head>
    <body class="body">
        <% if(udb!=null){%>
        <div class="main_container">
            <div class="userCtrl">
                    <h1>ユーザー登録情報</h1>
                <div class="userInfo">
                    <table class="mypageTable">
                        <tr>
                            <th class="column1">名前</th>
                            <td class="column2"><b><%= udb.getName() %></b></td>
                        </tr>
                        <tr>
                            <th class="column1">住所</th>
                            <td class="column2"><b><%= udb.getAddress() %></b></td>
                        </tr>
                        <tr>
                            <th class="column1">メールアドレス</th>
                            <td class="column2"><b><%= udb.getMail() %></b></td>
                        </tr>
                        <tr>
                            <th class="column1">パスワード</th>
                            <td class="column2"><b><%= udb.getPass() %></b></td>
                        </tr>
                    </table>
                </div>
                <div class="change_button">
                    <div class="update_bottun">
                    <form action="update" method="POST">
                        <input type="hidden" name="ac" value='<%=ac%>'>
                        <input type="submit" value="変更">
                    </form>
                    </div>
                    <div class="delete_bottun">
                    <form action="delete.jsp" method="POST">
                        <input type="hidden" name="ac" value='<%=ac%>'>
                        <input type="submit" value="削除">
                    </form>
                    </div>
                </div>
            </div>
                <% if(cartList!=null){ %>
                <div class="cartCtrl">
                    <h1>カートリスト:</h1>
                <form action="buyconfirm" method="post">
                    <% for(int j = 0 ; j < first ; j++ ){ %>
                    <div class="itemWrapper">
                        <% for( int i = j * 5 ;i < (j+1==first ? second+j*5 : 5+j*5) ; i++ ){ %>
                        <div class="item">
                            <img src='<%= cartList.get(i).getImage() %>' class="item_img">
                            <a href="inputcart?itemNum=<%= i %>"><p word-wrap="break-word"><font color="#0000ff" ><%= cartList.get(i).getItemCode() %></font></p></a>
                            <div class="item_footer">
                                <font color="#778899" size="2"> <%= cartList.get(i).getBrand() %></font><br>
                                <%= nfCur.format( cartList.get(i).getPrice() ) %><br>
                                <div class="star-rating" >
                                    <div class="star-rating-front" style="width: <%= cartList.get(i).getReviewPer() %>">★★★★★</div>
                                    <div class="star-rating-back">★★★★★</div>
                                    <div class="ratingNum"><%= cartList.get(i).getReviewRate() %> (<%=cartList.get(i).getReviewCount() %>)</div>
                                </div><br><br   >
                                数量：<%= cartList.get(i).getCount() %>個<br>
                                合計金額：<%= nfCur.format( cartList.get(i).getCount() *cartList.get(i).getPrice() ) %>
                                <% totalAmount+=cartList.get(i).getCount() *cartList.get(i).getPrice(); %>
                            </div>
                        </div>
                    <% } %>
                    <br>
                    </div>
                    <% } %><br>
                    <h1>合計金額:<%=nfCur.format( totalAmount )%></h1>
                        <input type="submit" value="購入画面へ進む" class="nextToBuy">
                </form>
  
                </div>
                    
        </div>
        <% } %>
        <% }else{ %>
            ユーザーが存在しません。<br>
        <% } %>
            <a href="buyhistory" class="nextToBuyHis">購入履歴画面へ進む</a>
            <a href="search" class="nextToSearch">検索画面へ</a>
    </body>
</html>
