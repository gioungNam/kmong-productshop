package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBConnectionUtil;

public class CartDao {

    /**
     * 장바구니 담기
     * @param userId
     * @param productNo
     * @param quantity
     * @return
     */
    public boolean addToCart(String userId, int productNo, int quantity) {
        String checkSql = "SELECT quantity FROM cart WHERE user_id = ? AND product_no = ?";
        String insertSql = "INSERT INTO cart (user_id, product_no, quantity) VALUES (?, ?, ?)";
        String updateSql = "UPDATE cart SET quantity = quantity + 1 WHERE user_id = ? AND product_no = ?";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            checkStmt.setString(1, userId);
            checkStmt.setInt(2, productNo);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                    updateStmt.setString(1, userId);
                    updateStmt.setInt(2, productNo);
                    updateStmt.executeUpdate();
                }
            } else {
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setString(1, userId);
                    insertStmt.setInt(2, productNo);
                    insertStmt.setInt(3, quantity);
                    insertStmt.executeUpdate();
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 특정 회원의 장바구니 리스트
     * @param userId
     * @return
     */
    public List<CartItem> getCartItems(String userId) {
        List<CartItem> items = new ArrayList<>();
        String sql = "SELECT p.name, m.name as makerName, c.quantity, p.price " +
                     "FROM cart c " +
                     "JOIN product p ON c.product_no = p.product_no " +
                     "JOIN maker m ON p.maker_id = m.id " +
                     "WHERE c.user_id = ?";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                CartItem item = new CartItem();
                item.setProductName(rs.getString("name"));
                item.setMakerName(rs.getString("makerName"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getInt("price"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
}
