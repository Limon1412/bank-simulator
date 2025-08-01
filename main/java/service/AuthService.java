package service;

import model.BankAccount;
import repository.AccountRepositoryImpl;
import repository.Database;
import valid.isValidPassword;


import java.util.Scanner;

public class AuthService {

    static AccountRepositoryImpl accountRepository = new AccountRepositoryImpl();

    public static boolean createUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя владельца банковского аккаунта:");
        String nameOwner = scanner.nextLine().trim();
        BankAccount bankAccount = new BankAccount(nameOwner);
        System.out.println("Введите пароль для доступа к аккаунту:");
        String password = scanner.nextLine().trim();
        while(!isValidPassword.validPassword(password)){
            System.out.println("Введите пароль:");
            password = scanner.nextLine().trim();
        }
        if (!Database.ACCOUNTS.containsKey(SecurityService.hashPassword(password,bankAccount.getAccountNumber()))) {
            bankAccount.setPassword(SecurityService.hashPassword(password,bankAccount.getAccountNumber()));
            System.out.println("Номер счёта: " + bankAccount.getAccountNumber());
            accountRepository.save(bankAccount);
            return true;
        } else{
            return false;
        }
    }

    public static boolean deleteUser(){
        System.out.println("Введите полный номер счёта(4 цифры):");
        Scanner scanner = new Scanner(System.in);
        String number = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();
        while(!isValidPassword.validPassword(password)){
            System.out.println("Введите пароль:");
            password = scanner.nextLine().trim();
        }
        if(Database.ACCOUNTS.containsKey(number)){
            if(Database.ACCOUNTS.get(number).getPassword().equals(SecurityService.hashPassword(password,number))){
                Database.ACCOUNTS.remove(number);return true;
            } else{
                System.out.println("Вы ввели неправильный пароль!");
                return false;
            }
        } else{
            System.out.println("Такого пользователя нет!");
            return false;
        }
    }

    public static BankAccount loginUser(){
        System.out.println("Введите полный номер счёта(4 цифры):");
        Scanner scanner = new Scanner(System.in);
        String number = scanner.nextLine();
        if(!number.matches("\\d{4}")){
            System.out.println("Ошибка, номер счёта должен состоять из 4 цифр!");
            return null;
        }
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();
        while(!isValidPassword.validPassword(password)){
            System.out.println("Введите пароль:");
            password = scanner.nextLine().trim();
        }
        BankAccount bankAccount = accountRepository.findById(number);
        if(bankAccount.getPassword().equals(SecurityService.hashPassword(password,bankAccount.getAccountNumber()))){
            return bankAccount;
        } else{
            return null;
        }
    }

}
