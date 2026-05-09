package banking_app.repository;

import banking_app.entity.customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<customer, Long> {
}