package repository;

import model.BankAccount;

import java.util.List;

public interface AccountRepository {
    BankAccount findById(String id);

    void save(BankAccount bankAccount);


    void delete(String number);
}
