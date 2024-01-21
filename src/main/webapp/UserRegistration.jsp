<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ユーザー登録</title>
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
    </style>
</head>
<body>
<div class="content">
     <h1>ユーザー登録</h1>

     <% String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) { %>
            <p style="color: red;"><%= errorMessage %></p>
     <% } %>

    <form action="./UserRegistration" method="post">
        <p>ユーザーID <input type="text" name="id" value=""></p>
        <p>ユーザー名 <input type="text" name="name" value=""></p>
        <p>パスワード <input type="password" name="pw" value=""></p>

        性別   :
        <label>
            <input type="radio" name="gender" value="male" checked> 男性
        </label>
        <label>
            <input type="radio" name="gender" value="female">女性 
        </label>
        <p>
            <button type="submit">登録</button>
        </p>
    </form>
</div>
</body>
</html>