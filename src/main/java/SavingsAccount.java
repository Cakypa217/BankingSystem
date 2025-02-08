import Interfaces.InterestBearing;
import Interfaces.TransactionValidator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SavingsAccount extends BankAccount implements InterestBearing, TransactionValidator {
    private static final BigDecimal INTEREST_RATE = BigDecimal.valueOf(0.05);
    private static final BigDecimal MONTHS_IN_YEAR = BigDecimal.valueOf(12);
    private static final BigDecimal UNTOUCHED_AMOUNT = BigDecimal.ZERO;

    public SavingsAccount(long accountNumber, BigDecimal balance, long accountHolder) {
        super(accountNumber, balance, accountHolder);
    }

    @Override
    public void withdraw(BigDecimal amount) {
        if (validate(amount)) {
            balance = balance.subtract(amount);
            System.out.println("Снято " + amount + " с накопительного счета.");
        } else {
            System.out.println("Невозможная сумма для снятия.");
        }
    }

    @Override
    public void applyInterest() {
        BigDecimal monthlyRate = INTEREST_RATE.divide(MONTHS_IN_YEAR, 2, RoundingMode.HALF_UP);
        BigDecimal interest = UNTOUCHED_AMOUNT.multiply(monthlyRate);
        balance = balance.add(interest);
        System.out.println("Начислены проценты на накопительный счет. Новый баланс: " + balance);
    }

    @Override
    public boolean validate(BigDecimal amount) {
        boolean isPositive = amount.compareTo(BigDecimal.ZERO) > 0;
        boolean hasSufficientFunds = balance.compareTo(amount) >= 0;
        return isPositive && hasSufficientFunds;
    }
}

