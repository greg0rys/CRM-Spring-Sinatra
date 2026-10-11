package shenefelt.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shenefelt.demo.entity.Customer;
import shenefelt.demo.repository.CustomerRepository;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customerRepository;

    // GET /api/customers - List all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // GET /api/customers/{id} - Fetch single customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/customers/by-email - Search customer by email
    @GetMapping("/by-email")
    public ResponseEntity<Customer> getCustomerByEmail(@RequestParam String email) {
        return customerRepository.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/customers - Create new customer
    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Email " + customer.getEmail() + " is already in use."));
        }
        Customer savedCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(savedCustomer);
    }

    // PUT /api/customers/{id} - Update existing customer
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedData) {
        return customerRepository.findById(id).map(existingCustomer -> {
            existingCustomer.setName(updatedData.getName());
            existingCustomer.setEmail(updatedData.getEmail());
            existingCustomer.setCompanyName(updatedData.getCompanyName());
            Customer saved = customerRepository.save(existingCustomer);
            return ResponseEntity.ok(saved);
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/customers/{id} - Delete customer
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        if (!customerRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        customerRepository.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "Customer deleted successfully."));
    }
}