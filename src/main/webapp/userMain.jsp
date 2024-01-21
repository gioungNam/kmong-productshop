<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ユーザー画面</title>
    <style>
        body { text-align: center; }
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

    <h1>ユーザー画面</h1>
    <p><button onclick="location.href='./product'">商品購入ページ</button></p>
    <p><button onclick="location.href='./cart'">カート·ページ</button></p>

</body>
</html>