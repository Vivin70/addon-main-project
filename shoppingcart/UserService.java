package shoppingcart;

import java.sql.*;

public class UserService {

    public int login(String username, String password) throws SQLException {
        PreparedStatement ps = DBConnection.con.prepareStatement(
            "SELECT id FROM users WHERE username=? AND password=?");
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("✅ Login successful!");
            return rs.getInt("id");
        } else {
            System.out.println("❌ Invalid credentials.");
            return -1;
        }
    }

    public void register(String username, String password) throws SQLException {
        PreparedStatement ps = DBConnection.con.prepareStatement(
            "INSERT INTO users (username, password) VALUES (?, ?)");
        ps.setString(1, username);
        ps.setString(2, password);
        ps.executeUpdate();
        System.out.println("✅ Registered successfully!");
    }
}
