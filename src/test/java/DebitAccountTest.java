import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DebitAccountTest {
    private DebitAccount debitAccount;
    private static final BigDecimal INITIAL_BALANCE = new BigDecimal("1000.00");
    private static final BigDecimal DEPOSIT_AMOUNT = new BigDecimal("500.00");
    private static final BigDecimal WITHDRAWAL_AMOUNT = new BigDecimal("500.00");
    private static final BigDecimal EXCEEDING_AMOUNT = new BigDecimal("2000.00");
    private static final BigDecimal NEGATIVE_AMOUNT = new BigDecimal("-100.00");
    private static final BigDecimal OVER_LIMIT_AMOUNT = new BigDecimal("11000.00");

    @BeforeEach
    void setUp() {
        debitAccount = new DebitAccount(1L, INITIAL_BALANCE, 1L);
    }

    @Test
    void depositShouldIncreaseBalance() {
        debitAccount.deposit(DEPOSIT_AMOUNT);
        assertEquals(new BigDecimal("1500.00"), debitAccount.getBalance());
    }

    @Test
    void withdrawShouldDecreaseBalance() {
        debitAccount.withdraw(WITHDRAWAL_AMOUNT);
        assertEquals(new BigDecimal("500.00"), debitAccount.getBalance());
    }

    @Test
    void withdrawShouldNotExceedBalance() {
        debitAccount.withdraw(EXCEEDING_AMOUNT);
        assertEquals(INITIAL_BALANCE, debitAccount.getBalance());
    }

    @Test
    void validateShouldReturnFalseForNegativeAmount() {
        assertFalse(debitAccount.validate(NEGATIVE_AMOUNT));
    }

    @Test
    void validateShouldReturnFalseForAmountExceedingLimit() {
        assertFalse(debitAccount.validate(OVER_LIMIT_AMOUNT));
    }
}
