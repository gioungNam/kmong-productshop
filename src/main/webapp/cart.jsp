<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="data.CartItem"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>カート</title>
    <style>
        body { 
            text-align: center; 
            display: flex;
            justify-content: center;
            align-items: flex-start; /* 상단 정렬 */
            height: 100vh;
            margin: 0;
        }
        .content {
            text-align: center;
        }
        .user-name { position: absolute; top: 20px; right: 20px; }
    </style>
</head>
<body>
    <div class="user-name">
        <% 
           String userName = (String) session.getAttribute("userName");
           if(userName != null) {
               out.println("こんにちは、" + userName + "さん");
           }
        %>
        <button onclick="location.href='./logout'">ログアウト</button>
    </div>
    <div class="content">
        <h1>カート</h1>
        <% 
        List<CartItem> cartItems = (List<CartItem>) request.getAttribute("cartItems");
        for (CartItem item : cartItems) {
        %>
            <div class="cart-item">
                <p><strong><%= item.getProductName() %></strong></p>
                <p>メーカー名: <%= item.getMakerName() %></p>
                <p>数量: <%= item.getQuantity() %></p>
                <p>合計価格: <%= item.getPrice() * item.getQuantity() %></p>
                <p>============</p>
            </div>
        <%
        }
        %>
        <p><button onclick="location.href='./logout'">購入</button><p>
    </div>
</body>
</html>