package org.example.exercices.exercice3.exception;
/**
 * BankAccount class represents a bank account with an ID and balance.
 */

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
