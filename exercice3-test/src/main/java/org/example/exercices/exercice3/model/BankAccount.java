package org.example.exercices.exercice3.model;
/**
 * BankAccount class represents a bank account with an ID and balance.
 */

public class BankAccount {
    private String id;
    private double balance;

    public BankAccount(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void debit(double amount) {
        balance -= amount;
    }

    public void credit(double amount) {
        balance += amount;
    }
}