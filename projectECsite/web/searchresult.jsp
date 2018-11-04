<%-- 
    Document   : searchresult
    Created on : 2017/09/17, 15:16:28
    Author     : guest1Day
--%>

<%@page 
    import="com.fasterxml.jackson.databind.JsonNode"
    import="com.fasterxml.jackson.databind.ObjectMapper"
    import="java.util.ArrayList"
    import="java.util.LinkedHashMap"
    import="java.util.Map"
    import="jums.ItemDataBeans"
    import="jums.UserDataBeans"
    import="java.text.NumberFormat"
    import="java.text.DecimalFormat"
%>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
        //ログインユーザー情報をmap配列paramsに格納
        ArrayList<ItemDataBeans> ibList = new ArrayList<ItemDataBeans>();
        ibList = (ArrayList<ItemDataBeans>)session.getAttribute("result");
        LinkedHashMap<String,String> params = (LinkedHashMap<String,String>)request.getAttribute("params");
        int offset = Integer.parseInt( params.get("offset") );
        //商品代金の表示を桁区切りで表示
        NumberFormat nfCur = NumberFormat.getCurrencyInstance();
        DecimalFormat df = new DecimalFormat("#,###");
        //ページの総数を変数に格納
        int totalResultsAvailable =  (Integer)request.getAttribute("totalResultsAvailable") ;
        //ログイン有無の確認
        Boolean loginCheck = false;
        String name ="";
        if( session.getAttribute("loginUser")!=null){
            loginCheck = true;
            UserDataBeans udb = (UserDataBeans)session.getAttribute("loginUser");
            name=udb.getName();
        }
        
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/searchresult.css">
        <link rel="stylesheet" type="text/css" href="css/header.css">
        <link rel="stylesheet" type="text/css" href="css/body.css">
        <link rel="stylesheet" type="text/css" href="css/productdisplay.css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <%--awesomeのリンクをDLしておく->このページに2アイコンあり--%>
        <title>JSP Page</title>
    </head>
    <body class="body">
        <div class="header"><%--ヘッド部分--%>
                <p class="header-left"><i class="fa fa-shopping-cart" aria-hidden="true"></i>shopping.com</p><%--awesomeのリンクをDLしておく--%>
            <div class="header-right">
            <% if( loginCheck == true){ %>
                <a href="mypage">アカウント名：<%= name%></a>
            <% }else{ %>
                <a href="login.jsp">ログイン</a>
            <% } %>
            </div>
        </div><%--ヘッド部分--%>
        <div class="main_container" ><%--ボディ部分--%>
        <h1>検索キーワード：<%= params.get("query") %></h1>
        <%--awesomeのリンクをDLしておく--%>
        <p><i class="fa fa-search" aria-hidden="true"></i>検索結果：<%= df.format( totalResultsAvailable )%>件中 <%= offset+1 %>-<%= offset + 20 %>件表示</p>
        <div class="item_box">
        <% for(int j = 0 ; j < 4 ; j++ ){ %>
        <div class="itemWrapper">
            <% for( int i = j * 5 ; i < 5 + j * 5 ; i++ ){ %>
                <div class="item">
                    <img src='<%= ibList.get(i).getImage() %>' class="itemImage">
                    <a href="inputcart?itemNum=<%= i %>"><p word-wrap="break-word"><font color="#0000ff" ><%= ibList.get(i).getName() %></font></p></a>
                    <div class="itemFooter">
                    <font color="#778899" size="2"> <%= ibList.get(i).getBrand() %></font><br>
                    <%= nfCur.format( Integer.parseInt( ibList.get(i).getPrice() ) ) %><br>
                    <div class="star-rating" >
                        <div class="star-rating-front" style="width: <%= ibList.get(i).getReviewPer() %>">★★★★★</div>
                        <div class="star-rating-back">★★★★★</div>
                        <div class="ratingNum"><%= ibList.get(i).getReviewRate() %> (<%=ibList.get(i).getReviewCount() %>)</div>
                    </div><br>
                    </div>
                </div>
            <% } %>
        </div>
            <br>
        <% } %>
        </div>
        </div><%--ボディ部分--%>
        <div class="pageButton"><%--フット部分--%>
            <% if( offset!=0){ %>
            <div class="pageBack">
                <form atcion="searchresult" method="GET">
                    <% params.put("offset",String.valueOf( offset-20) ) ;%>
                    <% for(Map.Entry<String,String> map : params.entrySet() ){ %>
                      <input type="hidden" name='<%= map.getKey() %>' value='<%= map.getValue() %>'>  
                    <% } %>
                    <input type="submit" value="<<前のページ">
                </form>   
            </div> 
            <% } %>
            <% if( totalResultsAvailable > offset + 20){ %>
            <div class="pageNext">
                <form atcion="searchresult" method="GET">
                    <% params.put("offset",String.valueOf( offset+20) ) ;%>
                    <% for(Map.Entry<String,String> map : params.entrySet() ){ %>
                      <input type="hidden" name='<%= map.getKey() %>' value='<%= map.getValue() %>'>  
                    <% } %>
                    <input type="submit" value="次のページ>>">
                </form>   
            </div>
            <% } %>
        </div><%--フット部分--%>
    </body>
</html>
