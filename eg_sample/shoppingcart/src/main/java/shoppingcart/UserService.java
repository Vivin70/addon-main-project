package shoppingcart;

import java.sql.*;

public class UserService {
    public int login(String username, String password) throws SQLException {
        PreparedStatement ps = DBConnection.con.prepareStatement(
            "SELECT id FROM users WHERE username=? AND password=?");
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        return rs.next() ? rs.getInt("id") : -1;
    }

    public boolean isAdmin(int id) throws SQLException {
        PreparedStatement ps = DBConnection.con.prepareStatement("SELECT is_admin FROM users WHERE id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        return rs.next() && rs.getBoolean("is_admin");
    }

    public void register(String username, String password) throws SQLException {
        PreparedStatement ps = DBConnection.con.prepareStatement(
            "INSERT INTO users (username, password, is_admin) VALUES (?, ?, false)");
        ps.setString(1, username);
        ps.setString(2, password);
        ps.executeUpdate();
    }
}