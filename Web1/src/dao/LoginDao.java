package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Member;

public class LoginDao {
    public static Member checkLogin(String username, String password) {
        String query = "SELECT id, pw, name, point FROM member WHERE id = ? AND pw = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String id = rs.getString("id");
                String pw = rs.getString("pw");
                String name = rs.getString("name");
                int point = rs.getInt("point");

                return new Member(id, pw, name, point, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
