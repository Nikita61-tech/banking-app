package banking_app.repository;

import banking_app.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT SUM(a.balance) FROM Account a")
    Double getTotalBankBalance();
}