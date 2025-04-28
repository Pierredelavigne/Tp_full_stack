package org.example.exercices.exercice3.service;

import org.example.exercices.exercice3.model.BankAccount;
import org.example.exercices.exercice3.exception.NotEnoughMoneyException;


import org.example.exercices.exercice3.model.BankAccount;
import org.example.exercices.exercice3.exception.NotEnoughMoneyException;

/**
 * BankAccount class represents a bank account with an ID and balance.
 */

public class BankService {

    public boolean transfer(BankAccount from, BankAccount to, Double amount) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Accounts must not be null.");
        }
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive and not null.");
        }
        if (from.getBalance() < amount) {
            throw new NotEnoughMoneyException("Not enough money to complete the transaction.");
        }

        from.debit(amount);
        to.credit(amount);

        return true;
    }
}
