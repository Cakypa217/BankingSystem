import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SavingsAccountTest {
    private static final BigDecimal INITIAL_BALANCE = new BigDecimal("1000.00");
    private static final BigDecimal EXCEEDING_AMOUNT = new BigDecimal("1200.00");
    private SavingsAccount savingsAccount;


    @BeforeEach
    void setUp() {
        savingsAccount = new SavingsAccount(3L, INITIAL_BALANCE, 3L);
    }

    @Test
    void applyInterestShouldIncreaseBalance() {
        BigDecimal initialBalance = savingsAccount.getBalance();
        savingsAccount.applyInterest();
        assertTrue(savingsAccount.getBalance().compareTo(initialBalance) >= 0);
    }

    @Test
    void withdrawShouldNotExceedBalance() {
        savingsAccount.withdraw(EXCEEDING_AMOUNT);
        assertEquals(INITIAL_BALANCE, savingsAccount.getBalance());
    }
}
