import lombok.Getter;

import java.math.BigDecimal;

@Getter
public abstract class BankAccount {
    protected long accountNumber;
    protected BigDecimal balance;
    protected long accountHolder;

    public BankAccount(long accountNumber, BigDecimal balance, long accountHolder) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountHolder = accountHolder;
    }


    public abstract void withdraw(BigDecimal amount);

    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            balance = balance.add(amount);
            System.out.println("Ваш баланс: " + balance);
        } else {
            System.out.println("Неверная сумма для пополнения.");
        }
    }
}
