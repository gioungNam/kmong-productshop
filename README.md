<h1>필독 부탁드립니다</h1>

**데이터 베이스 테이블 명세**

장바구니 테이블

CREATE TABLE `cart` (
  `cart_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) NOT NULL,
  `product_no` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci



메이커 테이블

CREATE TABLE `maker` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci



상품 테이블

CREATE TABLE `product` (
  `product_no` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `maker_id` int NOT NULL,
  `price` int DEFAULT NULL,
  PRIMARY KEY (`product_no`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci



유저 테이블

CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `sex` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci



**DB 설정 정보**

src\main\java\config\DBConfig.java 에서 필요시 수정하시면 됩니다.

아래는 현재 파일에 있는 값입니다.

```java
public class DBConfig {
        public static final String DATABASE_NAME = "jv4_db";
        public static final String PROPERTIES = "?characterEncoding=utf-8";
        // 필요시 변경하세요!
        public static final String DB_URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME + PROPERTIES;
        // 필요시 변경하세요!
        public static final String DB_USER = "root";
        // 필요시 변경하세요!
        public static final String DB_PASSWORD = "";
}
```





**실행 방법**

productshop.war 파일을 tomcat 서버의 webapps 폴더 하위에 위치시킨후 서버 구동시키면 됩니다.

기본 url은 http://localhost:8080/productshop 입니다.



**개발 정보**

- java17
- tomcat 9.0.41
- maven (종속성 라이브러리)



문의사항은 언제든지 편하게 크몽 메시지로 주시면 됩니다.

