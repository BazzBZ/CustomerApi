package de.senseiBZ.customerRESTApi.service;


import de.senseiBZ.customerRESTApi.entities.Customer;
import de.senseiBZ.customerRESTApi.repositories.CustomerRepo;
import org.springframework.stereotype.Service;

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
