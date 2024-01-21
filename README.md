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



**개발 사항**

[공통]

- 로그인 페이지

[어드민(관리자) side]
- 메인페이지 
- 유저 등록 페이지 
	- 유효성 체크 (아래 체크 통과못할시 유저 등록 페이지에 관련 실패 메시지 출력)
		- 아이디 (10자 이하인지?)
		- 비밀번호 (5 ~ 10자)
		- 닉네임 (20자 이내)
		- 이미 존재하는 회원인지? (id 중복 불가)
	- 성공시 완료페이지
- 상품 등록 페이지
	- 상품명 20자 이내인지 체크
	- 빈값 체크
	- 가격 정수형인지 체크
	- 성공시 완료페이지
- 메이커 등록 페이지 
	- 메이커명 20자 이내인지 체크
	- 빈값 아닌지 체크
	- 이미 존재하는 메이커명인지 체크
	- 성공시 완료페이지

[유저 side]
- 메인페이지 

- 상품 구입 페이지
  - 메이커별 조회 기능 
  - 검색 조회 기능
  - 가격 범위 조회
  - 아무 입력값 없을경우에는 전체 상품 조회

- 장바구니 페이지
	- 담은 상품 리스트 조회
	- 하단 구매 버튼 클릭시, 로그아웃 및 로그인 페이지 이동


- 로그인 관련 필터 추가
	- 일반 유저는 어드민 페이지 접근불가 (접근시 프론트페이지로 리디렉션)
	- 비회원은 어드민, 프론트 페이지 접근불가 (접근시 로그인페이지로 리디렉션)

- 어드민, 프론트 둘다 우측 상단에 로그아웃 버튼 기능 구현



문의사항은 언제든지 편하게 크몽 메시지로 주시면 됩니다 ^^

