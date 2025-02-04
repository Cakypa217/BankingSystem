import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DebitAccount debitAccount = new DebitAccount(
                1L, new BigDecimal("10000.00"), 1L);
        CreditAccount creditAccount = new CreditAccount(
                2L, new BigDecimal("2000.00"), 2L, new BigDecimal("5000.00"));
        SavingsAccount savingsAccount = new SavingsAccount(
                3L, new BigDecimal("5000.00"), 3L);

        List<BankAccount> accounts = List.of(debitAccount, creditAccount, savingsAccount);

        TransactionProcessor processor = new TransactionProcessor();

        processor.processTransaction(accounts, new BigDecimal("2000.00"));
        processor.processTransaction(accounts, new BigDecimal("500.00"));
        processor.processTransaction(accounts, new BigDecimal("7000.00"));
    }
}
