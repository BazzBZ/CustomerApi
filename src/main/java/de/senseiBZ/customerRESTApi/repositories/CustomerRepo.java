package de.senseiBZ.customerRESTApi.repositories;


import de.senseiBZ.customerRESTApi.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    Optional<Customer> findById(Integer integer);

}
