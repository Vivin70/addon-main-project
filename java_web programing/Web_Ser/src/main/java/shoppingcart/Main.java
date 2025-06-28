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

/*
package shoppingcart;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            new DBConnection();
            Scanner sc = new Scanner(System.in);
            UserService userService = new UserService();

            System.out.println("🔐 1. Register\n🔐 2. Login");
            int option = sc.nextInt();
            sc.nextLine();

            int userId = -1;
            boolean[] isAdmin = new boolean[1];

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

            userId = userService.login(username, password, isAdmin);

            if (userId == -1) return;

            if (isAdmin[0]) {
                new AdminFrame();
            } else {
                new ProductFrame(userId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/
