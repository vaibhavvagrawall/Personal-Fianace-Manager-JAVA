import java.util.Scanner;

public class Main {
    private static final String USER_DATA_FILE = "users.dat";
    private static final String FINANCE_DATA_FILE = "finance.dat";
    private static User currentUser;
    private static FinanceManager financeManager;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            switch (displayMainMen2u(scanner)) {
                case 1 -> register(scanner);
                case 2 -> {
                    if (login(scanner)) manageFinances(scanner);
                }
                case 3 -> exitProgram(scanner);
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static int displayMainMenu(Scanner scanner) {
        System.out.println("\nPersonal Finance Management System");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
        return scanner.nextInt();
    }

    private static void register(Scanner scanner) {
        scanner.nextLine(); // Clear the buffer
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User newUser = new User(username, password);
        FileManager.saveData(newUser, USER_DATA_FILE);
        System.out.println("Registration successful! Please log in.");
    }

    private static boolean login(Scanner scanner) {
        scanner.nextLine(); // Clear the buffer
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User savedUser = (User) FileManager.loadData(USER_DATA_FILE);
        if (savedUser != null && savedUser.getUsername().equals(username) && savedUser.authenticate(password)) {
            currentUser = savedUser;
            financeManager = (FinanceManager) FileManager.loadData(FINANCE_DATA_FILE);
            if (financeManager == null) {
                financeManager = new FinanceManager();
            }
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid username or password. Try again.");
            return false;
        }
    }

    private static void manageFinances(Scanner scanner) {
        while (true) {
            switch (displayFinanceMenu(scanner)) {
                case 1 -> addTransaction(scanner, "income");
                case 2 -> addTransaction(scanner, "expense");
                case 3 -> financeManager.generateReport();
                case 4 -> {
                    saveAndLogout();
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static int displayFinanceMenu(Scanner scanner) {
        System.out.println("\nManage Finances");
        System.out.println("1. Add Income");
        System.out.println("2. Add Expense");
        System.out.println("3. View Report");
        System.out.println("4. Logout");
        System.out.print("Choose an option: ");
        return scanner.nextInt();
    }

    private static void addTransaction(Scanner scanner, String type) {
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Clear the buffer
        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        financeManager.addTransaction(amount, category, type);
        System.out.println("Transaction added successfully!");
    }

    private static void saveAndLogout() {
        FileManager.saveData(financeManager, FINANCE_DATA_FILE);
        System.out.println("Logged out.");
    }

    private static void exitProgram(Scanner scanner) {
        System.out.println("Exiting...");
        scanner.close();
        System.exit(0);
    }
}