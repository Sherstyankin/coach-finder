package org.aston.repository;

import org.aston.entity.Customer;

import java.util.Set;

public interface CustomerRepository {
    Customer find(Long customerId);

    void save(Customer customer);

    void update(Customer customer);

    void delete(Long customerId);

    void assignCoach(Long customerId, Long coachId);

    Set<Customer> findCoachCustomers(Long coachId);
}
