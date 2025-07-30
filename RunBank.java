import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RunBank {
    public static void main(String[] args) {
        homeMenu();
    }

    private static void homeMenu(){
        System.out.println("Добро пожаловать в простой банковский симулятор");
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("Выберите, что хотите сделать:");
            System.out.println("1. Создать пользователя");
            System.out.println("2. Удалить пользователя");
            System.out.println("3. Зайти в аккаунт");
            System.out.println("4. Вывести список всех пользователей");
            System.out.println("5. Выйти из системы");
            try {
                byte choice = scanner.nextByte();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println("Создание пользователя........");
                        System.out.println("-------------------------------------");
                        if (createUser()) {
                            System.out.println("Пользователь успешно создан.");
                        } else {
                            System.out.println("Ошибка при создании.Такой пользователь уже есть, войдите в аккаунт!");
                        }
                        break;
                    case 2:
                        System.out.println("Удаление пользователя");
                        System.out.println("-------------------------------------");
                        if (deleteUser()) {
                            System.out.println("Пользователь успешно удален!");
                        } else {
                            System.out.println("Нн удалось удалить пользователя, проверьте пароль и номер счёта!");
                        }
                        break;
                    case 3:
                        System.out.println("Вход в аккаунт");
                        System.out.println("-------------------------------------");
                        BankAccount loggedInAccount = loginUser();
                        if (loggedInAccount != null) {
                            menu(loggedInAccount);
                        } else {
                            System.out.println("Не удалось войти в аккаунт, проверьте номер счёта и пароль!");
                        }
                        break;
                    case 4:
                        System.out.println("Список пользователей:");
                        for (var user : Database.ACCOUNTS.values()){
                            System.out.println(user);
                        }
                        break;
                    case 5:
                        System.out.println("Выходим из системы...........");
                        System.out.println("-------------------------------");
                        running = false;
                        break;
                    default:
                        System.out.println("Вы ввели неправильное значение, выберите снова!");
                }
            } catch(InputMismatchException exception){
                System.out.println("Введите число от 1 до 4!");
                scanner.nextLine();
            }
        }
    }

    private static boolean createUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя владельца банковского аккаунта:");
        String nameOwner = scanner.nextLine();
        BankAccount bankAccount = new BankAccount(nameOwner);
        System.out.println("Введите пароль для доступа к аккаунту:");
        String password = scanner.nextLine();
        if (!Database.ACCOUNTS.containsKey(password+bankAccount.getAccountNumber())) {
            Database.ACCOUNTS.put(password + bankAccount.getAccountNumber(), bankAccount);
            System.out.println("Номер счёта: " + bankAccount.getAccountNumber());
            return true;
        } else{
            return false;
        }
    }

    private static boolean deleteUser(){
        System.out.println("Введите полный номер счёта(4 цифры):");
        Scanner scanner = new Scanner(System.in);
        String number = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();
        if(Database.ACCOUNTS.containsKey(password+number)){
            Database.ACCOUNTS.remove(password+number);
            return true;
        } else{
            return false;
        }
    }

    private static BankAccount loginUser(){
        System.out.println("Введите полный номер счёта(4 цифры):");
        Scanner scanner = new Scanner(System.in);
        String number = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();
        return Database.ACCOUNTS.getOrDefault(password + number, null);
    }

    private static void menu(BankAccount bankAccount){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running){
            System.out.println("Выберите что хотите сделать:");
            System.out.println("1. Посмотреть баланс");
            System.out.println("2. Посмотреть аккаунт");
            System.out.println("3. Пополнить баланс");
            System.out.println("4. Снять с баланса деньги");
            System.out.println("5. Выйти в главное меню");

            try {
                byte choice = 0;
                choice = scanner.nextByte();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println(bankAccount.getBalance());
                        break;
                    case 2:
                        System.out.println(bankAccount.getAccount());
                        break;
                    case 3:
                        System.out.println("Введите на сколько хотите пополнить баланс: ");
                        BigDecimal amount = new BigDecimal(String.valueOf(scanner.nextBigDecimal()));
                        scanner.nextLine();
                        bankAccount.addBalance(amount);
                        break;
                    case 4:
                        System.out.println("Введите  сколько хотите снять с баланса: ");
                        BigDecimal amount1 = new BigDecimal(String.valueOf(scanner.nextBigDecimal()));
                        scanner.nextLine();
                        bankAccount.withdrawBalance(amount1);
                        break;
                    case 5:
                        System.out.println("Главное меню");
                        running = false;
                        break;
                    default:
                        System.out.println("Вы ввели неправильное значение, выберите снова!");
                }
            } catch(InputMismatchException exception){
                System.out.println("Введите число от 1 до 5!");
                scanner.nextLine();
            }
        }
    }

}
