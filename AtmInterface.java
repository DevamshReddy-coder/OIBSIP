import java.util.*;

class User {
    private String userId;
    private int userPin;

    public User(String userId, int userPin) {
        this.userId = userId;
        this.userPin = userPin;
    }

    public String getUserId() {
        return userId;
    }

    public int getUserPin() {
        return userPin;
    }
}

class ATM {
    private User currentUser;
    private double balance;
    private List<String> transactionsHistory = new ArrayList<>();

    public ATM(User u) {
        this.currentUser = u;
        this.balance = 10000.0;
    }

    public void displayMenu() {
        System.out.println("ATM Menu:");
        System.out.println("1. Transactions History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");
    }

    public void performOperation(int choice) {
        switch (choice) {
            case 1:
                displayTransactionsHistory();
                break;
            case 2:
                withdraw();
                break;
            case 3:
                deposit();
                break;
            case 4:
                transfer();
                break;
            case 5:
                System.out.println("Thank you for using the ATM. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void displayTransactionsHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionsHistory) {
            System.out.println(transaction);
        }
    }

    private void withdraw() {
        Scanner a = new Scanner(System.in);
        System.out.print("Enter withdrawal amount: ");
        double amount = a.nextDouble();

        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionsHistory.add("Withdrawal: -$" + amount);
            System.out.println("Withdrawal successful. Remaining balance: $" + balance);
        } else {
            System.out.println("Invalid amount or insufficient funds.");
        }
    }

    private void deposit() {
        Scanner a = new Scanner(System.in);
        System.out.print("Enter deposit amount: ");
        double amount = a.nextDouble();

        if (amount > 0) {
            balance += amount;
            transactionsHistory.add("Deposit: +$" + amount);
            System.out.println("Deposit successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid amount.");
        }
    }

    private void transfer() {
        Scanner a = new Scanner(System.in);
        System.out.print("Enter recipient's User ID: ");
        String recipientUserId = a.nextLine();
        if (recipientUserId.equals(currentUser.getUserId())) {
            System.out.println("Cannot transfer to the same account.");
            return;
        }

        System.out.print("Enter transfer amount: ");
        double amount = a.nextDouble();

        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionsHistory.add("Transfer to " + recipientUserId + ": -$" + amount);
            System.out.println("Transfer successful. Remaining balance: $" + balance);
        } else {
            System.out.println("Invalid amount or insufficient funds.");
        }
    }

    public void start() {
        Scanner a = new Scanner(System.in);

        System.out.print("Enter User ID: ");
        String userId = a.nextLine();
        System.out.print("Enter User PIN: ");
        int userPin = a.nextInt();

        if (currentUser.getUserId().equals(userId) && currentUser.getUserPin() == userPin) {
            System.out.println("Welcome, " + currentUser.getUserId() + "!");
            while (true) {
                displayMenu();
                System.out.print("Enter your choice: ");
                int choice = a.nextInt();
                a.nextLine();

                performOperation(choice);
            }
        } else {
            System.out.println("Invalid User ID or PIN. Exiting...");
        }
    }
}

public class AtmInterface {
    public static void main(String[] args) {
        User u = new User("Devamsh123", 5678);
        ATM a = new ATM(u);
        a.start();
    }
}
