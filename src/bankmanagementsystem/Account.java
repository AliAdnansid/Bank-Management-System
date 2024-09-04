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
class Account implements Serializable {
    private String accountType;
    private double balance;
    private String title;
    private Customer customer;

    public Account(String accountType, double balance, String title, Customer customer) {
        this.accountType = accountType;
        this.balance = balance;
        this.title = title;
        this.customer = customer;
    }

    // Getters and Setters
    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    // Transfer method
    public void transfer(Account toAccount, double amount) {
        if (amount > 0 && balance >= amount) {
            this.withdraw(amount);
            toAccount.deposit(amount);
        } else {
            System.out.println("Transfer failed: Insufficient balance or invalid amount.");
        }
    }

    @Override
    public String toString() {
        return "Account [accountType=" + accountType + ", balance=" + balance + ", title=" + title + ", customer=" + customer + "]";
    }
}
