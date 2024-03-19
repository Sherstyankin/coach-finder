package org.aston.service.impl;

import org.aston.dto.request.CustomerRequestDTO;
import org.aston.dto.response.CustomerResponseDTO;
import org.aston.dto.update.CustomerUpdateDTO;
import org.aston.entity.Customer;
import org.aston.repository.CustomerRepository;
import org.aston.repository.impl.CustomerRepositoryImpl;
import org.aston.service.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CustomerServiceImplTest {

    private final CustomerRepository mockCustomerRepository = Mockito.mock(CustomerRepositoryImpl.class);
    private final CustomerService customerService = new CustomerServiceImpl(mockCustomerRepository);

    private CustomerRequestDTO requestDTO;
    private CustomerResponseDTO responseDTO;
    private CustomerUpdateDTO updateDTO;
    private Customer customer;
    private Customer customerToSave;
    private Customer customerToUpdate;


    @BeforeEach
    void setUp() {
        requestDTO = CustomerRequestDTO.builder()
                .name("Sergey")
                .build();
        responseDTO = CustomerResponseDTO.builder()
                .id(1L)
                .name("Sergey")
                .coaches(Collections.emptySet())
                .build();
        updateDTO = CustomerUpdateDTO.builder()
                .id(1L)
                .name("Andrey")
                .build();
        customer = Customer.builder()
                .id(1L)
                .name("Sergey")
                .build();
        customerToSave = Customer.builder()
                .name("Sergey")
                .build();
        customerToUpdate = Customer.builder()
                .id(1L)
                .name("Andrey")
                .build();
    }

    @AfterEach
    void tearDown() {
        requestDTO = null;
        responseDTO = null;
        updateDTO = null;
        customer = null;
        customerToSave = null;
        customerToUpdate = null;
    }

    @ParameterizedTest
    @ValueSource(longs = {1, Long.MAX_VALUE})
    void findCustomerByIdThenReturnDTO(Long id) {
        Mockito
                .when(mockCustomerRepository.find(id))
                .thenReturn(customer);

        CustomerResponseDTO result = customerService.find(id);
        Assertions.assertEquals(responseDTO, result);
    }

    @Test
    void saveCustomer() {
        customerService.save(requestDTO);
        verify(mockCustomerRepository, times(1)).save(customerToSave);
    }

    @Test
    void updateCustomer() {
        customerService.update(updateDTO);
        verify(mockCustomerRepository, times(1)).update(customerToUpdate);
    }

    @Test
    void delete() {
        customerService.delete(1L);
        verify(mockCustomerRepository, times(1)).delete(1L);
    }

    @Test
    void assignCoach() {
        customerService.assignCoach(1L, 1L);
        verify(mockCustomerRepository, times(1)).assignCoach(1L, 1L);
    }

    @Test
    void findCoachCustomers() {
        Set<Customer> customers = new HashSet<>();
        customers.add(customer);
        Mockito
                .when(mockCustomerRepository.findCoachCustomers(1L))
                .thenReturn(customers);

        Set<Customer> result = customerService.findCoachCustomers(1L);
        Assertions.assertEquals(customers, result);
    }
}