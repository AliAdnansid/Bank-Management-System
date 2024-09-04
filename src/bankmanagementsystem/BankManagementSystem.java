package bankmanagementsystem;
import java.io.*;
import java.util.*;

public class BankManagementSystem {

    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        // Load existing data
        bank.loadData("accounts.dat");

        while (true) {
            System.out.println("\nBank Management System Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. Delete Account");
            System.out.println("3. Update Account");
            System.out.println("4. Deposit Money");
            System.out.println("5. Withdraw Money");
            System.out.println("6. Transfer Money");
            System.out.println("7. Show All Accounts");
            System.out.println("8. Save and Exit");

            System.out.print("Select an option (1-8): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createAccount(bank, scanner);
                    break;
                case 2:
                    deleteAccount(bank, scanner);
                    break;
                case 3:
                    updateAccount(bank, scanner);
                    break;
                case 4:
                    depositMoney(bank, scanner);
                    break;
                case 5:
                    withdrawMoney(bank, scanner);
                    break;
                case 6:
                    transferMoney(bank, scanner);
                    break;
                case 7:
                    bank.showAllAccounts();
                    break;
                case 8:
                    bank.saveData("accounts.dat");
                    System.out.println("Data saved. Exiting.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private static void createAccount(Bank bank, Scanner scanner) {
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Customer Address: ");
        String address = scanner.nextLine();

        System.out.print("Enter Customer Phone Number: ");
        String phoneNumber = scanner.nextLine();

        Customer customer = new Customer(name, address, phoneNumber);

        System.out.print("Enter Account Type (e.g., Savings, Checking): ");
        String accountType = scanner.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Account Title: ");
        String title = scanner.nextLine();

        bank.createAccount(accountType, balance, title, customer);
        System.out.println("Account created successfully.");
    }

    private static void deleteAccount(Bank bank, Scanner scanner) {
        System.out.print("Enter Account Title to Delete: ");
        String title = scanner.nextLine();

        bank.deleteAccount(title);
        System.out.println("Account deleted if it existed.");
    }

    private static void updateAccount(Bank bank, Scanner scanner) {
        System.out.print("Enter Account Title to Update: ");
        String title = scanner.nextLine();

        System.out.print("Enter New Account Type: ");
        String accountType = scanner.nextLine();

        System.out.print("Enter New Balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        bank.updateAccount(title, accountType, balance);
        System.out.println("Account updated if it existed.");
    }

    private static void depositMoney(Bank bank, Scanner scanner) {
        System.out.print("Enter Account Title to Deposit: ");
        String title = scanner.nextLine();

        System.out.print("Enter Amount to Deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Account account = bank.findAccountByTitle(title);
        if (account != null) {
            account.deposit(amount);
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdrawMoney(Bank bank, Scanner scanner) {
        System.out.print("Enter Account Title to Withdraw: ");
        String title = scanner.nextLine();

        System.out.print("Enter Amount to Withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Account account = bank.findAccountByTitle(title);
        if (account != null) {
            account.withdraw(amount);
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Account not found or insufficient balance.");
        }
    }

    private static void transferMoney(Bank bank, Scanner scanner) {
        System.out.print("Enter Source Account Title: ");
        String sourceTitle = scanner.nextLine();

        System.out.print("Enter Destination Account Title: ");
        String destinationTitle = scanner.nextLine();

        System.out.print("Enter Amount to Transfer: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Account sourceAccount = bank.findAccountByTitle(sourceTitle);
        Account destinationAccount = bank.findAccountByTitle(destinationTitle);

        if (sourceAccount != null && destinationAccount != null) {
            sourceAccount.transfer(destinationAccount, amount);
            System.out.println("Transfer successful.");
        } else {
            System.out.println("One or both accounts not found.");
        }
    }
}
