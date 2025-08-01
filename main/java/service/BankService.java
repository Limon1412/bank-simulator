package service;

import model.BankAccount;
import model.Transaction;
import repository.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class BankService{

    public static void transfer(BankAccount accountFrom, BankAccount accountTo, BigDecimal amount){
        synchronized (BankService.class) {
            accountFrom.setBalance(accountFrom.getBalance().subtract(amount));
            accountTo.setBalance(accountTo.getBalance().add(amount));
            accountFrom.setTransactions(new Transaction(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),TransactionType.TRANSFER,amount));
            accountTo.setTransactions(new Transaction(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),TransactionType.DEPOSIT,amount));
        }
    }
}
