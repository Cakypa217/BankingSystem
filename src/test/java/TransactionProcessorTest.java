import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionProcessorTest {
    private static final BigDecimal INITIAL_BALANCE = new BigDecimal("1000.00");
    private static final BigDecimal CREDIT_LIMIT = new BigDecimal("5000.00");
    private static final BigDecimal TRANSACTION_AMOUNT = new BigDecimal("500.00");
    private static final BigDecimal SMALL_AMOUNT = new BigDecimal("100.00");
    private TransactionProcessor processor;
    private List<BankAccount> accounts;

    @BeforeEach
    void setUp() {
        processor = new TransactionProcessor();
        accounts = new ArrayList<>(Arrays.asList(
                new DebitAccount(1L, INITIAL_BALANCE, 1L),
                new CreditAccount(2L, INITIAL_BALANCE, 2L, CREDIT_LIMIT),
                new SavingsAccount(3L, INITIAL_BALANCE, 3L)
        ));
    }

    @Test
    void processTransactionShouldAffectAllAccounts() {
        processor.processTransaction(accounts, TRANSACTION_AMOUNT);

        for (BankAccount account : accounts) {
            assertTrue(account.getBalance().compareTo(INITIAL_BALANCE) < 0,
                    "Баланс должен быть меньше начального");
        }
    }

    @Test
    void processTransactionShouldHandleEmptyList() {
        accounts.clear();
        assertDoesNotThrow(() -> processor.processTransaction(accounts, SMALL_AMOUNT));
    }

    @Test
    void processTransactionShouldRespectAccountLimits() {
        BigDecimal largeAmount = new BigDecimal("2000.00");
        processor.processTransaction(accounts, largeAmount);

        assertTrue(accounts.get(0).getBalance().compareTo(BigDecimal.ZERO) >= 0,
                "Дебетовый счет не должен иметь отрицательный баланс");
    }

    @Test
    void processTransactionShouldHandleNegativeAmount() {
        BigDecimal negativeAmount = new BigDecimal("-100.00");
        processor.processTransaction(accounts, negativeAmount);

        for (BankAccount account : accounts) {
            assertEquals(INITIAL_BALANCE, account.getBalance(),
                    "Баланс не должен измениться при отрицательной сумме");
        }
    }
}

