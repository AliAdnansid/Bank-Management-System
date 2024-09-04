/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package bankmanagementsystem;
import java.io.*;
import java.util.*;

/**
 *
 * @author Ali
 */
class Bank implements Serializable {
    private List<Account> accounts;

    public Bank() {
        this.accounts = new ArrayList<>();
    }

    // Create a new account
    public void createAccount(String accountType, double balance, String title, Customer customer) {
        Account account = new Account(accountType, balance, title, customer);
        accounts.add(account);
    }

    // Delete an account
    public void deleteAccount(String title) {
        Account account = findAccountByTitle(title);
        if (account != null) {
            accounts.remove(account);
        } else {
            System.out.println("Account not found.");
        }
    }

    // Update account information
    public void updateAccount(String title, String newAccountType, double newBalance) {
        Account account = findAccountByTitle(title);
        if (account != null) {
            account.setAccountType(newAccountType);
            account.setBalance(newBalance);
        } else {
            System.out.println("Account not found.");
        }
    }

    // Find an account by title
    public Account findAccountByTitle(String title) {
        for (Account account : accounts) {
            if (account.getTitle().equals(title)) {
                return account;
            }
        }
        return null;
    }

    // Save data to file
    public void saveData(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load data from file
    public void loadData(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            accounts = (List<Account>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Show all accounts
    public void showAllAccounts() {
        for (Account account : accounts) {
            System.out.println(account);
        }
    }
}
