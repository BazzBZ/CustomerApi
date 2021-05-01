package de.senseiBZ.customerRESTApi.controller;

import de.senseiBZ.customerRESTApi.entities.Customer;
import de.senseiBZ.customerRESTApi.repositories.CustomerRepo;
import de.senseiBZ.customerRESTApi.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.DrbgParameters;
import java.util.Optional;

@RestController
public class Controller {
    private final CustomerService customerService;

    public Controller(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getCustomerMail")
    public String getCustomerMailByID(@RequestParam(name = "id", required = true) int id) {
        Optional<Customer> customer = this.customerService.getCustomerByID(id);
        if (customer.isPresent()) {
            return customer.get().getEmail();
        } else
            return null;
    }

    @DeleteMapping("/deleteCustomerByID")
    public ResponseEntity<String> deleteCustomerByID(@RequestParam(name = "id", required = true) int id) {
        Optional<Customer> customer = this.customerService.getCustomerByID(id);
        if (customer.isPresent()) {
            this.customerService.deleteCustomer(customer.get());
            return ResponseEntity.ok("Successful deleted ");
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    @PostMapping("/addCustomer")
    public ResponseEntity<Customer> addCustomer(@RequestBody(required = true) final Customer customer) {
        this.customerService.addCustomer(customer);
        return ResponseEntity.ok().body(customer);
    }
}
