<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>完了</title>
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
    <h1>完了</h1>
    <p><%= request.getAttribute("message") %></p>
    <p>
        <button onclick="window.location.href='./main'">管理画面</button>
    </p>
</div>
</body>
</html>