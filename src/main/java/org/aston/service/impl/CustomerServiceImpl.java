package org.aston.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.aston.dto.request.CustomerRequestDTO;
import org.aston.dto.response.CustomerResponseDTO;
import org.aston.dto.update.CustomerUpdateDTO;
import org.aston.entity.Customer;
import org.aston.mapper.CustomerMapper;
import org.aston.repository.CustomerRepository;
import org.aston.repository.impl.CustomerRepositoryImpl;
import org.aston.service.CustomerService;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository = new CustomerRepositoryImpl();

    @Override
    public CustomerResponseDTO find(Long id) {
        Customer customer = customerRepository.find(id);
        return CustomerMapper.toDTO(customer);
    }

    @Override
    public void save(CustomerRequestDTO dto) {
        Customer customer = CustomerMapper.toEntity(dto);
        customerRepository.save(customer);
    }

    @Override
    public void update(CustomerUpdateDTO dto) {
        Customer customer = CustomerMapper.toEntity(dto);
        customerRepository.update(customer);
    }

    @Override
    public void delete(Long id) {
        customerRepository.delete(id);
    }

    @Override
    public void assignCoach(Long customerId, Long coachId) {
        customerRepository.assignCoach(customerId, coachId);
    }

    @Override
    public Set<Customer> findCoachCustomers(Long coachId) {
        return customerRepository.findCoachCustomers(coachId);
    }
}
