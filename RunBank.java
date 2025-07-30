import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RunBank {
    public static void run(){
        BankAccount bankAccount = create();
        menu(bankAccount);

    }

    private static BankAccount create(){
        System.out.println("Добро пожаловать в простой банковский симулятор");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер счёта:");
        String accountNumber = scanner.nextLine();
        scanner = new Scanner(System.in);
        System.out.println("Введите имя владельца банковского аккаунта:");
        String nameOwner = scanner.nextLine();
        BankAccount bankAccount = new BankAccount(nameOwner,accountNumber);
        System.out.println("Аккаунт для " + nameOwner + " успешно создан!");
        return bankAccount;
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
            System.out.println("5. Выйти из системы");

            try {
                byte choice = 0;
                choice = scanner.nextByte();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println(getBalance(bankAccount));
                        break;
                    case 2:
                        System.out.println(bankAccount.getAccount());
                        break;
                    case 3:
                        System.out.println("Введите на сколько хотите пополнить баланс: ");
                        BigDecimal amount = new BigDecimal(String.valueOf(scanner.nextBigDecimal()));
                        scanner.nextLine();
                        addBalance(bankAccount, amount);
                        break;
                    case 4:
                        System.out.println("Введите  сколько хотите снять с баланса: ");
                        BigDecimal amount1 = new BigDecimal(String.valueOf(scanner.nextBigDecimal()));
                        scanner.nextLine();
                        withdrawBalance(bankAccount, amount1);
                        break;
                    case 5:
                        System.out.println("Выходим из системы..........");
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
        scanner.close();
    }

    private static String getBalance(BankAccount account){
        return "Ваш баланс: " + account.getBalance();
    }

    private static void addBalance(BankAccount bankAccount, BigDecimal amount){
        bankAccount.addBalance(amount);
        System.out.println("Ваш баланс пополнен на " + amount);
    }

    private static void withdrawBalance(BankAccount bankAccount, BigDecimal amount){
        if (amount.compareTo(bankAccount.getBalance()) > 0){
            System.out.println("Сумма для снятия больше, чем на балансе!");
            System.out.println("Сумма, доступная для снятия: " + bankAccount.getBalance());
        }else {
            bankAccount.withdrawBalance(amount);
            System.out.println("С вашего баланса было снять " + amount);
        }
    }
}
