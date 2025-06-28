package shoppingcart;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    public static Connection con;
    public static Statement stmt;

    public DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/add_on_project";
        String username = "root";
        String password = "vivin";
        con = DriverManager.getConnection(url, username, password);
        stmt = con.createStatement();
    }
}
