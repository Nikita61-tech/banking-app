package banking_app.service;

import banking_app.repository.AccountRepository;
import banking_app.repository.CustomerRepository;
import banking_app.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Map<String, Object> getDashboardData() {

        Map<String, Object> data = new HashMap<>();

        data.put("totalCustomers", customerRepository.count());

        data.put("totalAccounts", accountRepository.count());

        data.put("totalTransactions", transactionRepository.count());

        data.put("totalBankBalance", accountRepository.getTotalBankBalance());

        return data;
    }
}