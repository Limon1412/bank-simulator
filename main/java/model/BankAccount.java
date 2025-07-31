package model;

import java.math.BigDecimal;

public class BankAccount {

    private final String nameOwner;

    private final String accountNumber;

    private BigDecimal balance;

    private static Integer numberforAcc = 1;

    private String password;

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

    public String getAccount(){
        return "Счёт " + accountNumber + "\n" + "Владелец: " + nameOwner + "\n"
                + "Баланс счёта: " + balance;
    }

    public void addBalance(BigDecimal balance){
        this.balance =this.balance.add(balance);
    }

    public void withdrawBalance(BigDecimal balance){
            this.balance = this.balance.subtract(balance);
    }

    @Override
    public String toString() {
        return nameOwner;
    }
}
