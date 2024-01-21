<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="data.Maker"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品検索</title>
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
    <form action="./prdsearch" method="get"> 
       <h1>商品検索</h1>
       <p>商品名 <input type="text" name="productName" value=""></p>

        <label for="select">メーカー名</label>
        <select id="select" name="makerName">
            <option value="0">----</option>
             <% 
            List<Maker> makers = (List<Maker>) request.getAttribute("makers");
            for (Maker maker : makers) {
                out.println("<option value=\"" + maker.getId() + "\">" + maker.getName() + "</option>");
            }
            %>
        </select>

        <p>最低価格 <input type="text" name="minPrice" value=""></p>
        <p>最高価格 <input type="text" name="maxPrice" value=""></p>

        <p><button type="submit">検索</button></p>
    </form>
    <div id="searchResults">
    </div>
</div>

<script>
    function searchProducts() {
        var formData = {
            productName: document.getElementsByName("productName")[0].value,
            makerName: document.getElementById("select").value,
            minPrice: document.getElementsByName("minPrice")[0].value,
            maxPrice: document.getElementsByName("maxPrice")[0].value
        };

        fetch('./prdsearch?' + new URLSearchParams(formData))
            .then(response => response.json())
            .then(data => {
                var resultDiv = document.getElementById("searchResults");
                resultDiv.innerHTML = ""; // 결과를 담을 div 초기화
                data.forEach(product => {
                    var productInfo = "<div class='product-info'>" +
                                      "<strong>"+ product.name +"</strong><br>" +
                                      "メーカー名: " + product.makerName + "<br>" +
                                      "価格: " + product.price + "<br>" +
                                      "<p><button onclick='addToCart(" + product.productNo + ")'>カートに追加</button></p>" + 
                                      "============" +
                                      "</div>";
                    resultDiv.innerHTML += productInfo;
                });
            })
            .catch(error => console.error('Error:', error));
    }

    document.querySelector("form").addEventListener("submit", function(event){
        event.preventDefault();
        searchProducts();
    });

    function addToCart(productNo) {
        fetch('./cart', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: 'productNo=' + productNo
        })
        .then(response => {
            if (response.ok) {
                return response.text();
            } else {
                throw new Error('カートに商品を追加できませんでした。');
            }
        })
        .then(text => {
            if (confirm("カートページに移動しますか？")) {
                window.location.href = './cart'; 
            }
        })
        .catch(error => console.error('Error:', error));
    }
</script>
</body>
</html>