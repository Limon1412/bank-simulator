import model.BankAccount;
import service.AuthService;

import java.util.InputMismatchException;
import java.util.Scanner;
import repository.Database;


public class HomeMenu {
    public static void homeMenu(){
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
                        if (AuthService.createUser()) {
                            System.out.println("Пользователь успешно создан.");
                        } else {
                            System.out.println("Ошибка при создании.Такой пользователь уже есть, войдите в аккаунт!");
                        }
                        break;
                    case 2:
                        System.out.println("Удаление пользователя");
                        System.out.println("-------------------------------------");
                        if (AuthService.deleteUser()) {
                            System.out.println("Пользователь успешно удален!");
                        } else {
                            System.out.println("Нн удалось удалить пользователя, проверьте пароль и номер счёта!");
                        }
                        break;
                    case 3:
                        System.out.println("Вход в аккаунт");
                        System.out.println("-------------------------------------");
                        BankAccount loggedInAccount = AuthService.loginUser();
                        if (loggedInAccount != null) {
                            Menu.menu(loggedInAccount);
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
}
