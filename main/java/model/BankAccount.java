package model;

import repository.TransactionType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class BankAccount implements Serializable {

    private final String nameOwner;

    private final String accountNumber;

    private BigDecimal balance;

    private String password;

    private static Integer numberforAcc = 1;
    
    private List<Transaction> transactions = new ArrayList<>();

    public BankAccount(String nameOwner){
        this.nameOwner = nameOwner;
        this.accountNumber = "0".repeat(4-Integer.toString(numberforAcc).length()) + Integer.toString(numberforAcc);
        numberforAcc++;
        this.balance = new BigDecimal("0.0");
    }

    public BigDecimal getBalance(){
        return balance;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getAccountNumber(){
        return this.accountNumber;
    }

    public void setTransactions(Transaction transaction){
        transactions.add(transaction);
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void changePassword(String s){
        password = s;
    }

    public List<Transaction> getTransactions(){
        return transactions;
    }
    public String getAccount(){
        return "Счёт " + accountNumber + "\n" + "Владелец: " + nameOwner + "\n"
                + "Баланс счёта: " + balance;
    }

    public void addBalance(BigDecimal balance){
        this.balance =this.balance.add(balance);
        transactions.add(new Transaction(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), TransactionType.DEPOSIT,balance));
    }

    public void withdrawBalance(BigDecimal balance){
            this.balance = this.balance.subtract(balance);
            transactions.add(new Transaction(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), TransactionType.WITHDRAW,balance));
    }

    @Override
    public String toString() {
        return nameOwner;
    }
}
