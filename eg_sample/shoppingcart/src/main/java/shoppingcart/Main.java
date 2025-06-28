package shoppingcart;

public class Main {
    public static void main(String[] args) {
        try {
            new DBConnection();
            new LoginFrame();
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }
}