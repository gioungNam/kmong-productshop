package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBConnectionUtil;

public class MakerDao {

    /**
     * 메이커 존재여부 체크
     * @param name
     * @return
     */
    public boolean isMakerExists(String name) {
        String sql = "SELECT COUNT(*) FROM maker WHERE name = ?";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 메이커 등록
     * @param name
     * @return
     */
    public boolean addMaker(String name) {
        String sql = "INSERT INTO maker (name) VALUES (?)";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 메이커 조회
     * @return
     */
    public List<Maker> getAllMakerNames() {
        List<Maker> makers = new ArrayList<>();
        String sql = "SELECT id, name FROM maker";

        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Maker maker = new Maker();
                maker.setId(rs.getInt("id"));
                maker.setName(rs.getString("name"));
                makers.add(maker);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return makers;
    }
}