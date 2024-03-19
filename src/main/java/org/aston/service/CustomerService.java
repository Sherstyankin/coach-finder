package org.aston.service;

import org.aston.dto.request.CustomerRequestDTO;
import org.aston.dto.response.CustomerResponseDTO;
import org.aston.dto.update.CustomerUpdateDTO;
import org.aston.entity.Customer;

import java.util.Set;

public interface CustomerService {
    CustomerResponseDTO find(Long id);

    void save(CustomerRequestDTO dto);

    void update(CustomerUpdateDTO dto);

    void delete(Long id);

    void assignCoach(Long customerId, Long coachId);

    Set<Customer> findCoachCustomers(Long coachId);
}
