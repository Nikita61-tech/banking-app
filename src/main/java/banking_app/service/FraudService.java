package banking_app.service;

import org.springframework.stereotype.Service;

@Service
public class FraudService {

    public boolean isFraud(Double amount, Double currentBalance) {

        // Rule 1: Large transaction
        if (amount > 50000) {
            return true;
        }

        // Rule 2: withdrawing most of balance
        if (amount > currentBalance * 0.9) {
            return true;
        }

        return false;
    }
}