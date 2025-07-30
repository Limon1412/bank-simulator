import java.math.BigDecimal;

public class BankAccount {

    private String nameOwner;

    private String accountNumber;

    private BigDecimal balance;

    public BankAccount(String nameOwner, String accountNumber){
        this.nameOwner = nameOwner;
        this.accountNumber = accountNumber;
        this.balance = new BigDecimal("0.0");
    }

    public BigDecimal getBalance(){
        return balance;
    }

    public String getAccount(){
        return "Счёт " + accountNumber + "\n" + "Владелец: " + nameOwner + "\n"
                + "Баланс счёта: " + balance;
    }

    public void addBalance(BigDecimal balance){
        this.balance =this.balance.add(balance);
    }

    public void withdrawBalance(BigDecimal balance){
        this.balance = this.balance.subtract(balance);
    }
}
