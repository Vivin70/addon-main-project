package shoppingcart;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // Initialize DB connection
            new DBConnection();

            Scanner sc = new Scanner(System.in);
            UserService userService = new UserService();
            ProductService productService = new ProductService();
            CartService cartService = new CartService();

            System.out.println("🔐 1. Register\n🔐 2. Login");
            int option = sc.nextInt();
            sc.nextLine();

            int userId = -1;
            if (option == 1) {
                System.out.print("Enter username: ");
                String user = sc.nextLine();
                System.out.print("Enter password: ");
                String pass = sc.nextLine();
                userService.register(user, pass);
            }

            System.out.print("Username: ");
            String username = sc.nextLine();
            System.out.print("Password: ");
            String password = sc.nextLine();
            userId = userService.login(username, password);

            if (userId == -1) return;

            while (true) {
                System.out.println("\n🛒 MENU:");
                System.out.println("1. View Products");
                System.out.println("2. Add to Cart");
                System.out.println("3. View Cart");
                System.out.println("4. Checkout");
                System.out.println("5. Exit");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        productService.showProducts();
                        break;
                    case 2:
                        System.out.print("Enter Product ID: ");
                        int pid = sc.nextInt();
                        System.out.print("Enter Quantity: ");
                        int qty = sc.nextInt();
                        cartService.addToCart(userId, pid, qty);
                        break;
                    case 3:
                        cartService.viewCart(userId);
                        break;
                    case 4:
                        cartService.checkout(userId);
                        break;
                    case 5:
                        System.out.println("👋 Thank you for shopping!");
                        System.exit(0);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
