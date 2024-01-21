package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DBConnectionUtil;

public class UserDao {
    public User authenticate(String id, String password) {
        User user = null;
        String sql = "SELECT * FROM user WHERE id = ? AND password = ?";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 유저 등록
     * @param id
     * @param name
     * @param password
     * @param gender
     * @return
     */
    public boolean registerUser(String id, String name, String password, String gender) {
        String sql = "INSERT INTO user (id, name, password, sex) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, password);
            pstmt.setString(4, gender);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 유저 존재 여부 체크
     * @param userId
     * @return
     */
    public boolean isUserIdExists(String userId) {
        String sql = "SELECT COUNT(*) FROM user WHERE id = ?";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // ID가 존재하면 true 반환
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // 쿼리 실패 시 false 반환
    }
}
