package repository;

import model.BankAccount;

import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Database {
    private static final String DEFAULT_FILE_NAME = "users.dat";

    public  static Map<String, BankAccount> ACCOUNTS = new HashMap<>();

    public static String getDefaultFilePath(){
        return new File(DEFAULT_FILE_NAME).getAbsolutePath();
    }
    public static void saveToFile(){
        saveToFile(DEFAULT_FILE_NAME);
    }

    public static void saveToFile(String filePath) {
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))){
            outputStream.writeObject(new HashMap<>(ACCOUNTS));
            System.out.println("Данные сохранены в " + filePath + " (пользователей: " +ACCOUNTS.size() +")");
        } catch (IOException e){
            System.err.println("Ошибка сохранения: " + e.getMessage());
        }
    }

    public static void loadFromFile(){
        loadFromFile(DEFAULT_FILE_NAME);
    }

    public static void loadFromFile(String filePath){
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Файл " + filePath + "не найден. Будет создан новый");
            ACCOUNTS = new HashMap<>();
            return;
        }

        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))){
            Object obj = inputStream.readObject();
            if (obj instanceof Map){
                ACCOUNTS = new HashMap<>((Map<String, BankAccount>) obj);
                System.out.println("Загружено " + ACCOUNTS.size() + " пользователей из " +filePath);
            }
        } catch (Exception e){
            System.err.println("Ошибка загрузки: " + e.getMessage());
            ACCOUNTS = new HashMap<>();
        }
    }

}
