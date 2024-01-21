package data;

public class Product {
    private int productNo;
    private String name;
    private int makerId;
    private int price;
    private String makerName; // maker 테이블과 조인된 결과를 저장하기 위한 필드

    // 기본 생성자
    public Product() {
    }

    // productNo에 대한 getter 및 setter
    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    // name에 대한 getter 및 setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // makerId에 대한 getter 및 setter
    public int getMakerId() {
        return makerId;
    }

    public void setMakerId(int makerId) {
        this.makerId = makerId;
    }

    // price에 대한 getter 및 setter
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // makerName에 대한 getter 및 setter
    public String getMakerName() {
        return makerName;
    }

    public void setMakerName(String makerName) {
        this.makerName = makerName;
    }
}
