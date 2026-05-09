package banking_app.service;

import banking_app.entity.Account;
import banking_app.entity.Transaction;
import banking_app.repository.AccountRepository;
import banking_app.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private FraudService fraudService;

    public Account deposit(Long accountId, Double amount) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance() + amount);

        Transaction txn = new Transaction();
        txn.setType("DEPOSIT");
        txn.setAmount(amount);
        txn.setTimestamp(LocalDateTime.now());
        txn.setAccount(account);

        Double currentBalance = account.getBalance();

        account.setBalance(currentBalance + amount);

        System.out.println("Amount: " + amount);
        System.out.println("Fraud? " + fraudService.isFraud(amount, currentBalance));

        boolean isFraud = fraudService.isFraud(amount, currentBalance);

        System.out.println("Fraud check: " + isFraud);

        txn.setFlagged(isFraud);

        transactionRepository.save(txn);

        return accountRepository.save(account);
    }

    public Account withdraw(Long accountId, Double amount) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Double currentBalance = account.getBalance();  // store BEFORE update

        if (currentBalance < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(currentBalance - amount);

        Transaction txn = new Transaction();
        txn.setType("WITHDRAW");
        txn.setAmount(amount);
        txn.setTimestamp(LocalDateTime.now());
        txn.setAccount(account);

//  use OLD balance
        boolean isFraud = fraudService.isFraud(amount, currentBalance);
        txn.setFlagged(isFraud);

        transactionRepository.save(txn);

        return accountRepository.save(account);
    }
}