package banking_app.controller;

import banking_app.entity.customer;
import banking_app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public customer createCustomer(@RequestBody customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping
    public List<customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}