package model;

import repository.TransactionType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction implements Serializable {

    public Transaction(LocalDateTime date,TransactionType type, BigDecimal amount){
        this.date = date;
        this.type = type;
        this.amount = amount;
    }
    private final LocalDateTime date;

    private final TransactionType type;

    private final BigDecimal amount;

    public LocalDateTime getDate() {
        return date;
    }


    public TransactionType getType() {
        return this.type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Дата операции: " + date
                + "   Тип операции: " + type
                + "   Сумма операции: " + amount;
    }
}
