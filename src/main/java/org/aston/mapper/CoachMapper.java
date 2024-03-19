package org.aston.mapper;

import lombok.experimental.UtilityClass;
import org.aston.dto.request.CoachRequestDTO;
import org.aston.dto.response.CoachResponseDTO;
import org.aston.dto.response.GymResponseDTO;
import org.aston.dto.update.CoachUpdateDTO;
import org.aston.entity.Coach;
import org.aston.service.CustomerService;
import org.aston.service.impl.CustomerServiceImpl;

@UtilityClass
public class CoachMapper {

    private final CustomerService customerService = new CustomerServiceImpl();

    public Coach toEntity(CoachRequestDTO dto) {
        return Coach.builder()
                .name(dto.getName())
                .sportType(dto.getSportType())
                .gymId(dto.getGymId())
                .build();
    }

    public Coach toEntity(CoachUpdateDTO dto) {
        return Coach.builder()
                .id(dto.getId())
                .name(dto.getName())
                .sportType(dto.getSportType())
                .gymId(dto.getGymId())
                .build();
    }

    public CoachResponseDTO toDTO(Coach coach, GymResponseDTO gym) {
        return CoachResponseDTO.builder()
                .id(coach.getId())
                .name(coach.getName())
                .sportType(coach.getSportType())
                .gym(gym)
                .customers(customerService.findCoachCustomers(coach.getId()))
                .build();
    }
}
