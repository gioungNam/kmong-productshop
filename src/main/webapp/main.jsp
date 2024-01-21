<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String message = (String)request.getAttribute("MESSAGE");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login page</title>
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
    <h1>ログイン</h1>
    <form action="./LoginServlet" method="post">
<%if(message != null ){%>
<p><%= request.getAttribute("MESSAGE") %></p><% }%>

		<p>ID : <input type="text" name="ID" value=""></p>
		
		<p>password : <input type="password" name="password" value=""></p>
		
		<p><button type="submit">ログイン</button></p>
		
		</form>
</div>

</body>
</html>