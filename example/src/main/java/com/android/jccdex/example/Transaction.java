package com.android.jccdex.example;

public class Transaction {

    private String Account;
    private String TransactionType;
    private String Destination;
    private double Fee;
    private int Flags;
    private int Amount;
    private int Sequence;

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getTransactionType() {
        return TransactionType;
    }

    public void setTransactionType(String transactionType) {
        TransactionType = transactionType;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public double getFee() {
        return Fee;
    }

    public void setFee(double fee) {
        Fee = fee;
    }

    public int getFlags() {
        return Flags;
    }

    public void setFlags(int flags) {
        Flags = flags;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public int getSequence() {
        return Sequence;
    }

    public void setSequence(int sequence) {
        Sequence = sequence;
    }
}
