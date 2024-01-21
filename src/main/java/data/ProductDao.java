package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBConnectionUtil;

public class ProductDao {
    
    /**
     * 상품 추가
     * @param name
     * @param makerId
     * @param price
     * @return
     */
    public boolean addProduct(String name, int makerId, int price) {
        String sql = "INSERT INTO product (name, maker_id, price) VALUES (?, ?, ?)";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setInt(2, makerId);
            pstmt.setInt(3, price);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 상품 검색
     * @param productName
     * @param makerName
     * @param minPrice
     * @param maxPrice
     * @return
     */
     public List<Product> searchProducts(String productName, String makerIdStr, String minPriceStr, String maxPriceStr) {
        
    
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.product_no, p.name, p.price, m.name as maker_name " +
                     "FROM product p INNER JOIN maker m ON p.maker_id = m.id WHERE 1=1 ";

        // 조건 추가
        List<Object> params = new ArrayList<>();
        if (productName != null && !productName.isBlank()) {
            sql += "AND p.name LIKE ? ";
            params.add("%" + productName + "%");
        }
        if (makerIdStr != null && !makerIdStr.isBlank() && !"0".equals(makerIdStr)) {
            sql += "AND p.maker_id = ? ";
            params.add(Integer.parseInt(makerIdStr));
        }
        if (minPriceStr != null && !minPriceStr.isBlank()) {
            sql += "AND p.price >= ? ";
            params.add(Integer.parseInt(minPriceStr));
        }
        if (maxPriceStr != null && !maxPriceStr.isBlank()) {
            sql += "AND p.price <= ? ";
            params.add(Integer.parseInt(maxPriceStr));
        }

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductNo(rs.getInt("product_no"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setMakerName(rs.getString("maker_name"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
}
