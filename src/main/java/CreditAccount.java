import Interfaces.TransactionFee;
import Interfaces.TransactionValidator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CreditAccount extends BankAccount implements TransactionFee, TransactionValidator {
    private static final BigDecimal MAX_AMOUNT = BigDecimal.valueOf(5000);
    private static final BigDecimal commission = BigDecimal.valueOf(0.01);
    private static final int DECIMAL_PLACES = 2;
    private BigDecimal creditLimit;

    public CreditAccount(long accountNumber, BigDecimal balance, long accountHolder, BigDecimal creditLimit) {
        super(accountNumber, balance, accountHolder);
        this.creditLimit = creditLimit;
    }

    @Override
    public void withdraw(BigDecimal amount) {
        if (validate(amount)) {
            applyFee(amount);
            balance = balance.subtract(amount).setScale(DECIMAL_PLACES, RoundingMode.HALF_UP);
            System.out.println("Средства сняты. Ваш баланс: " + balance);
        } else {
            System.out.println("Невозможная сумма для снятия.");
        }
    }

    @Override
    public void applyFee(BigDecimal amount) {
        BigDecimal fee = amount.multiply(commission).setScale(DECIMAL_PLACES, RoundingMode.HALF_UP);
        balance = balance.subtract(fee).setScale(DECIMAL_PLACES, RoundingMode.HALF_UP);
        System.out.println("Комиссия за снятие средств: " + fee);
    }

    @Override
    public boolean validate(BigDecimal amount) {
        boolean isPositive = amount.compareTo(BigDecimal.ZERO) > 0;
        BigDecimal totalDeduction = amount.add(amount.multiply(commission));
        boolean withinLimit = balance.subtract(totalDeduction).compareTo(creditLimit.negate()) >= 0;
        boolean belowMaxAmount = amount.compareTo(MAX_AMOUNT) < 0;
        return isPositive && withinLimit && belowMaxAmount;
    }
}

