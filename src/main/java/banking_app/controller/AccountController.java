package banking_app.controller;

import java.util.List;
import banking_app.entity.Account;
import banking_app.entity.customer;
import banking_app.repository.AccountRepository;
import banking_app.repository.CustomerRepository;
import banking_app.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import banking_app.entity.Transaction;
import java.time.LocalDateTime;
import banking_app.service.AccountService;

import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountService accountService;

    @PostMapping("/{customerId}")
    public Account createAccount(@PathVariable Long customerId, @RequestBody Account account) {

        Optional<customer> customer = customerRepository.findById(customerId);

        if (customer.isPresent()) {
            account.setCustomer(customer.get());
            return accountRepository.save(account);
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    @PutMapping("/deposit/{accountId}")
    public Account deposit(@PathVariable Long accountId,
                           @RequestParam Double amount) {
        return accountService.deposit(accountId, amount);
    }

    @PutMapping("/withdraw/{accountId}")
    public Account withdraw(@PathVariable Long accountId,
                            @RequestParam Double amount) {
        return accountService.withdraw(accountId, amount);
    }

    @GetMapping("/fraud-transactions/{accountId}")
    public List<Transaction> getTransactions(@PathVariable Long accountId) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        return transactionRepository.findByAccount(account);
    }
}

