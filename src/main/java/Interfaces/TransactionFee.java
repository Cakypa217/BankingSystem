package Interfaces;

import java.math.BigDecimal;

public interface TransactionFee {
    void applyFee(BigDecimal amount);
}
