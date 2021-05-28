package de.senseiBZ.customerRESTApi.controller;

import de.senseiBZ.customerRESTApi.entities.Customer;
import de.senseiBZ.customerRESTApi.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
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
        return customer.map(Customer::getEmail).orElse(null);
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
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody(required = true) final Customer customer) {
        this.customerService.addCustomer(customer);
        return ResponseEntity.ok().body(customer);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(final MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
