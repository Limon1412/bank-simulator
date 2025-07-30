import java.math.BigDecimal;

public class BankAccount {

    private final String nameOwner;

    private final String accountNumber;

    private BigDecimal balance;

    private static Integer numberforAcc = 1;

    public BankAccount(String nameOwner){
        this.nameOwner = nameOwner;
        this.accountNumber = "0".repeat(4-Integer.toString(numberforAcc).length()) + Integer.toString(numberforAcc);
        numberforAcc++;
        this.balance = new BigDecimal("0.0");
    }

    public String getBalance(){
        return "Ваш баланс: " + balance;
    }
    public String getAccountNumber(){
        return this.accountNumber;
    }

    public String getAccount(){
        return "Счёт " + accountNumber + "\n" + "Владелец: " + nameOwner + "\n"
                + "Баланс счёта: " + balance;
    }

    public void addBalance(BigDecimal balance){
        this.balance =this.balance.add(balance);
        System.out.println("Ваш баланс пополнен на " + balance);
    }

    public void withdrawBalance(BigDecimal balance){
        if (balance.compareTo(this.balance) > 0){
            System.out.println("Сумма для снятия больше, чем на балансе!");
            System.out.println("Сумма, доступная для снятия: " + this.balance);
        }else {
            this.balance = this.balance.subtract(balance);
            System.out.println("С вашего баланса было снято " + balance);
        }
    }

    @Override
    public String toString() {
        return nameOwner;
    }
}
