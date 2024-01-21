<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理画面</title>
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
        <h1>管理画面</h1>
        <form action="./AdmingMainServlet" method="post">
            <p><button type="submit" name="action" value="UR">ユーザー登録</button></p>
            <p><button type="submit" name="action" value="MR">メーカー登録</button></p>
            <p><button type="submit" name="action" value="MM">商品管理</button></p>
        </form>
    </div>
</body>
</html>