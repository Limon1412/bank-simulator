package valid;

import java.util.ArrayList;
import java.util.List;

public class isValidPassword {
    public static boolean validPassword(String password){

        List<String> errors = new ArrayList<>();

        if(password.length()<8) {
            errors.add("Длина строки должна быть больше или равна 8 символам!");
        }
        if (!password.matches(".*[A-Z].*")) {
            errors.add("Строка должна содержать хотя бы 1 заглавную букву(A-Z)");
        }
        if (!password.matches(".*[a-z].*")) {
            errors.add("Должна быть хотя бы одна строчная буква (a-z)");
        }
        if(!password.matches(".*[~!@#$%^&*()_+].*")){
            errors.add("Строка должна содержать хотя бы 1 специальный символ(~!@#$%^&*()_+)!");
        }
        if (!password.matches(".*\\d.*")) {
            errors.add("Должна быть хотя бы одна цифра (0-9)");
        }

        if (!errors.isEmpty()){
            System.out.println("Ошибки в пароле:");
            errors.forEach(System.out::println);
            return false;
        }

        return  true;
    }
}
