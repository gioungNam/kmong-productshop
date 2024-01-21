<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="data.Maker"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品管理</title>
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
        <h1>商品管理</h1>
        
        <% String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) { %>
            <p style="color: red;"><%= errorMessage %></p>
        <% } %>

        <form action="./ProductAdmin" method="post">
            <p>商品名 <input type="text" name="productName" value=""/></p>

            <label for="select">メーカー名</label>
            <select id="select" name="makerId">
                <option value="0">----</option>
                <% 
                List<Maker> makers = (List<Maker>) request.getAttribute("makers");
                for (Maker maker : makers) {
                    out.println("<option value=\"" + maker.getId() + "\">" + maker.getName() + "</option>");
                }
                %>
            </select>

            <p>価格 <input type="text" name="price" value=""/></p>

            <p><button type="submit">登録</button></p>
        </form>
    </div>

</body>
</html>