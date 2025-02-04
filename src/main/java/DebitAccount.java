import Interfaces.TransactionValidator;

import java.math.BigDecimal;

public class DebitAccount extends BankAccount implements TransactionValidator {
    private static final BigDecimal MAX_AMOUNT = BigDecimal.valueOf(10000);

    public DebitAccount(long accountNumber, BigDecimal balance, long accountHolder) {
        super(accountNumber, balance, accountHolder);
    }

    @Override
    public void withdraw(BigDecimal amount) {
        if (validate(amount)) {
            balance = balance.subtract(amount);
            System.out.println("Средства сняты. Ваш баланс : " + balance);
        } else {
            System.out.println("Невозможная сумма для снятия.");
        }
    }

    @Override
    public boolean validate(BigDecimal amount) {
        boolean isPositive = amount.compareTo(BigDecimal.ZERO) > 0;
        boolean hasSufficientFunds = balance.compareTo(amount) >= 0;
        boolean belowMaxAmount = amount.compareTo(MAX_AMOUNT) < 0;

        return isPositive && hasSufficientFunds && belowMaxAmount;
    }
}
