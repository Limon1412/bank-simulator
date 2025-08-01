import model.BankAccount;
import model.Transaction;
import repository.Database;
import service.BankService;
import service.SecurityService;
import valid.isValidPassword;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void menu(BankAccount bankAccount){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running){
            System.out.println("Выберите что хотите сделать:");
            System.out.println("1. Посмотреть баланс");
            System.out.println("2. Посмотреть аккаунт");
            System.out.println("3. Пополнить баланс");
            System.out.println("4. Снять с баланса деньги");
            System.out.println("5. Перевести деньги на другой счёт");
            System.out.println("6. История операций");
            System.out.println("7. Поменять пароль");
            System.out.println("8. Выйти в главное меню");

            try {
                byte choice = 0;
                choice = scanner.nextByte();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println("------------------------------");
                        System.out.println("Ваш баланс: " + bankAccount.getBalance());
                        System.out.println();
                        break;
                    case 2:
                        System.out.println("------------------------------");
                        System.out.println(bankAccount.getAccount());
                        System.out.println();
                        break;
                    case 3:
                        System.out.println("Пополнение баланса");
                        System.out.println("----------------------");
                        boolean validInput = false;
                        BigDecimal amount = BigDecimal.ZERO;
                        while(!validInput) {
                            try {
                                System.out.println("Введите сумму для пополнения (или 'отмена' для выхода): ");
                                String input = scanner.nextLine().trim();

                                if (input.equalsIgnoreCase("отмена")){
                                    break;
                                }

                                amount = new BigDecimal(input);
                                if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                                    System.out.println("Сумма должна быть больше нуля!");
                                    continue;
                                }

                                bankAccount.addBalance(amount);
                                System.out.println("Баланс успешно пополнен на " + amount);
                                validInput = true;
                            } catch (NumberFormatException exception) {
                                System.out.println("Ошибка: введите корректное число!");
                            } catch (Exception e){
                                System.out.println("Ошибка: " + e.getMessage());
                            }
                        }
                        System.out.println();
                        break;
                        case 4:
                            System.out.println("Снятие с баланса");
                            System.out.println("----------------------");
                            boolean validWithdraw = false;
                            BigDecimal amountWithdraw = BigDecimal.ZERO;
                            while(!validWithdraw) {
                                try {
                                    System.out.println("Введите сумму для снятия (или 'отмена' для выхода): ");
                                    String input = scanner.nextLine().trim();

                                    if (input.equalsIgnoreCase("отмена")){
                                        break;
                                    }

                                    amountWithdraw = new BigDecimal(input);
                                    if (amountWithdraw.compareTo(BigDecimal.ZERO) <= 0) {
                                        System.out.println("Сумма должна быть больше нуля!");
                                        continue;
                                    }
                                    if(amountWithdraw.compareTo(bankAccount.getBalance()) <=0){
                                        bankAccount.withdrawBalance(amountWithdraw);
                                        System.out.println("С вашего баланса успешно списано " + amountWithdraw);
                                        validWithdraw = true;
                                    } else{
                                        System.out.println("Сумма для снятия превышает сумму, доступную на балансе!");
                                        System.out.println("Сумма доступная для снятия: " + bankAccount.getBalance());
                                        continue;
                                    }
                                } catch (NumberFormatException exception) {
                                    System.out.println("Ошибка: введите корректное число!");
                                } catch (Exception e){
                                    System.out.println("Ошибка: " + e.getMessage());
                                }
                            }
                            System.out.println();
                            break;
                    case 5:
                        System.out.println("Перевод денег");
                        System.out.println("----------------------");
                        boolean validTransfer = false;
                        BigDecimal amountTransfer = BigDecimal.ZERO;
                        while(!validTransfer) {
                            try {
                                System.out.println("Введите счёт, на который нужно перевести(или 'отмена' для выхода):");
                                String accountNumberTo = scanner.nextLine().trim();
                                if (accountNumberTo.equalsIgnoreCase("отмена")){
                                    break;
                                }
                                if (!accountNumberTo.matches("\\d{4}")){
                                    System.out.println("Номер счёта должен состоять из 4 цифр!");
                                    continue;
                                }
                                if(accountNumberTo.equals(bankAccount.getAccountNumber())){
                                    System.out.println("Счёт для перевода должен быть отличен от изначального!");
                                    continue;
                                }

                                if (Database.ACCOUNTS.containsKey(accountNumberTo)){
                                    System.out.println("Введите сумму для пополнения (или 'отмена' для выхода): ");
                                    String input = scanner.nextLine().trim();

                                    if (input.equalsIgnoreCase("отмена")){
                                        break;
                                    }
                                    amountTransfer = new BigDecimal(input);
                                    if (amountTransfer.compareTo(BigDecimal.ZERO) <= 0) {
                                        System.out.println("Сумма перевода должна быть больше нуля!");
                                        continue;
                                    }
                                    if (bankAccount.getBalance().compareTo(amountTransfer)<0){
                                        System.out.println("На счёте недостаточно средств");
                                        continue;
                                    }
                                    BankService.transfer(bankAccount,Database.ACCOUNTS.get(accountNumberTo),amountTransfer);
                                    System.out.println("С вашего баланса списано: " + amountTransfer);
                                    System.out.println("Деньги доставлены на счёт " + accountNumberTo);
                                    validTransfer = true;
                                } else{
                                    System.out.println("Аккаунт не найден!");
                                }

                            } catch (NumberFormatException exception) {
                                System.out.println("Ошибка: введите корректное число!");
                            } catch (Exception e){
                                System.out.println("Ошибка: " + e.getMessage());
                            }
                        }
                        System.out.println();
                        break;
                    case 6:
                        System.out.println("История операций");
                        System.out.println("-----------------------");
                        List<Transaction> transactions = bankAccount.getTransactions();
                        for(var transaction : transactions){
                            System.out.println(transaction);
                        }
                        System.out.println();
                        break;
                    case 7:
                        System.out.println("Изменение пароля");
                        System.out.println("-----------------------");
                        boolean validPassword = false;
                        System.out.println("Введите текущий пароль:");
                        while (!validPassword) {
                            String passwordPrev = scanner.nextLine().trim();
                            if (passwordPrev.equalsIgnoreCase("отмена")){
                                break;
                            }
                            if (SecurityService.hashPassword(passwordPrev, bankAccount.getAccountNumber())
                                    .equals(bankAccount.getPassword())) {
                                System.out.println("Введите новый пароль:");
                                String passwordNew = scanner.nextLine().trim();
                                while(!isValidPassword.validPassword(passwordNew)){
                                    System.out.println("Введите пароль:");
                                    passwordNew = scanner.nextLine().trim();
                                }
                                bankAccount.setPassword(SecurityService.hashPassword(passwordNew, bankAccount.getAccountNumber()));
                                validPassword = true;
                            } else {
                                System.out.println("Неверный пароль!");
                                System.out.println("Введите снова(или 'отмена' для выхода).");
                            }
                        }
                        break;
                    case 8:
                        System.out.println("Главное меню");
                        running = false;
                        System.out.println();
                        break;
                    default:
                        System.out.println("Вы ввели неправильное значение, выберите снова!");
                }
            } catch(InputMismatchException exception){
                System.out.println("Введите число от 1 до 7!");
                scanner.nextLine();
            } catch (Exception e){
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
}
