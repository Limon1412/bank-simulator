package repository;

import model.BankAccount;


import static repository.Database.ACCOUNTS;

public class AccountRepositoryImpl implements AccountRepository {

    @Override
    public BankAccount findById(String id) {
        return ACCOUNTS.getOrDefault(id,null);
    }

    @Override
    public void save(BankAccount bankAccount) {
        String number = bankAccount.getAccountNumber();
        ACCOUNTS.put(number, bankAccount);
    }

    @Override
    public void delete(String number) {
        ACCOUNTS.remove(number);
    }
}
