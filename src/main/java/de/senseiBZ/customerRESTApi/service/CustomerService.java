package de.senseiBZ.customerRESTApi.service;


import de.senseiBZ.customerRESTApi.entities.Customer;
import de.senseiBZ.customerRESTApi.repositories.CustomerRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepo customerRepository;

    public CustomerService(CustomerRepo customerRepo){
        this.customerRepository = customerRepo;
    }

    public Optional<Customer> getCustomerByID(final int id){
        return this.customerRepository.findById(id);
    }
    public void addCustomer(final Customer customer){
        this.customerRepository.save(customer);

    }
    public void deleteCustomer(final Customer customer){
        this.customerRepository.delete(customer);
    }


}
