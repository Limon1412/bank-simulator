package repository;

import model.BankAccount;


public interface AccountRepository {
    BankAccount findById(String id);

    void save(BankAccount bankAccount);


    void delete(String number);
}
