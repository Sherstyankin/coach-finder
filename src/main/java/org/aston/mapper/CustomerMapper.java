package org.aston.mapper;

import lombok.experimental.UtilityClass;
import org.aston.dto.request.CustomerRequestDTO;
import org.aston.dto.response.CustomerResponseDTO;
import org.aston.dto.update.CustomerUpdateDTO;
import org.aston.entity.Customer;
import org.aston.service.CoachService;
import org.aston.service.impl.CoachServiceImpl;

@UtilityClass
public class CustomerMapper {

    private CoachService coachService = new CoachServiceImpl();

    public Customer toEntity(CustomerRequestDTO dto) {
        return Customer.builder()
                .name(dto.getName())
                .build();
    }

    public Customer toEntity(CustomerUpdateDTO dto) {
        return Customer.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    public CustomerResponseDTO toDTO(Customer customer) {
        return CustomerResponseDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .coaches(coachService.findCustomerCoaches(customer.getId()))
                .build();
    }
}
