import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CreditAccountTest {
    private CreditAccount creditAccount;
    private static final BigDecimal INITIAL_BALANCE = new BigDecimal("1000.00");
    private static final BigDecimal CREDIT_LIMIT = new BigDecimal("5000.00");
    private static final BigDecimal WITHDRAWAL_AMOUNT = new BigDecimal("100.00");
    private static final BigDecimal LARGE_WITHDRAWAL = new BigDecimal("3000.00");
    private static final BigDecimal EXCEEDING_AMOUNT = new BigDecimal("5500.00");

    @BeforeEach
    void setUp() {
        creditAccount = new CreditAccount(2L, INITIAL_BALANCE, 2L, CREDIT_LIMIT);
    }

    @Test
    void withdrawShouldApplyCommission() {
        creditAccount.withdraw(WITHDRAWAL_AMOUNT);
        assertEquals(new BigDecimal("899.00"), creditAccount.getBalance());
    }

    @Test
    void withdrawShouldAllowNegativeBalanceWithinLimit() {
        creditAccount.withdraw(LARGE_WITHDRAWAL);
        assertTrue(creditAccount.getBalance().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(creditAccount.getBalance().compareTo(CREDIT_LIMIT.negate()) >= 0);
    }

    @Test
    void validateShouldReturnFalseForAmountExceedingLimit() {
        assertFalse(creditAccount.validate(EXCEEDING_AMOUNT));
    }
}
