package banking_app.repository;

import banking_app.entity.Transaction;
import banking_app.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccount(Account account);
    List<Transaction> findByAccountAndFlaggedTrue(Account account);
}