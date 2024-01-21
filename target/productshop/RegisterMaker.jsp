<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メーカー登録</title>
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
    <h1>メーカー登録</h1>

    <% String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) { %>
            <p style="color: red;"><%= errorMessage %></p>
     <% } %>

    <form action="./MakerSql" method="post">
        <p>メーカー名 : <input type="text" name="makerName" value=""></p>
        <p><button type="submit">登録</button></p>
    </form>
</div>
</body>
</html>