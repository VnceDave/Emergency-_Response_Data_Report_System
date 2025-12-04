import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Load stored reports first
        File_Handling.loadReports(); // static load into CRUD_Function.reports
        ConsoleUI ui = new ConsoleUI();
        ui.showMainMenu();
    }
}

class ConsoleUI {
    private final Scanner sc = new Scanner(System.in);
    private final CRUD_Function crud = new CRUD_Function();

    void showMainMenu() {
        while (true) {
            clearScreen();
            System.out.println("=== EMERGENCY RESPONSE DATA SYSTEM ===");
            System.out.println("[1] User");
            System.out.println("[2] Authorized Person");
            System.out.println("[3] Exit");
            System.out.print("Choose: ");
            String c = sc.nextLine().trim();

            switch (c) {
                case "1" : {
                    // Anyone can access user menu
                    System.out.println("\n[INFO] Logged in as USER");
                    pressEnterToContinue();
                    crud.showUserMenu();
                }
                case "2" : {
                    System.out.print("Username: ");
                    String user = sc.nextLine().trim();
                    System.out.print("Password: ");
                    String pass = sc.nextLine().trim();

                    if ("admin".equals(user) && "1234".equals(pass)) {
                        System.out.println("[INFO] Login Successful!");
                        pressEnterToContinue();
                        crud.showAuthorizedMenu();
                    } else {
                        System.out.println("[ERROR] Invalid credentials!");
                        pressEnterToContinue();
                    }
                }
                case "3" : {
                    System.out.println("Saving data and exiting...");
                    File_Handling.saveReports(); // persist before exit
                    System.out.println("Goodbye!");
                    return;
                }
                default : {
                    System.out.println("Invalid option. Try again.");
                    pressEnterToContinue();
                }
            }
        }
    }

    void clearScreen() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // fallback
            for (int i = 0; i < 30; i++) System.out.println();
        }
    }

    void pressEnterToContinue() {
        System.out.print("\nPress ENTER to continue...");
        sc.nextLine();
    }
}


