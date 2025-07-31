package service;

import model.BankAccount;

import java.math.BigDecimal;

public class BankService {

    public static void transfer(BankAccount accountNumberFrom, BankAccount accountNumberTo, BigDecimal amount){
        synchronized (BankService.class) {
            accountNumberFrom.withdrawBalance(amount);
            accountNumberTo.addBalance(amount);
        }
    }

}
