package shoppingcart;

import java.sql.*;

public class DBConnection {
    public static Connection con;
    public static Statement stmt;

    public DBConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/add_on_project", "root", "vivin");
        stmt = con.createStatement();
    }
}
