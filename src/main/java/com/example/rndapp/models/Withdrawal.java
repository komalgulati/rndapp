package com.example.rndapp.models;

import java.sql.Timestamp;

public class Withdrawal {
    private String accountId;
    private String currency;
    private double amount;
    private double balance = 500.00;
    private String transactionId;
    private Timestamp tranTime;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Timestamp getTranTime() {
        return tranTime;
    }

    public void setTranTime(Timestamp tranTime) {
        this.tranTime = tranTime;
    }
}
