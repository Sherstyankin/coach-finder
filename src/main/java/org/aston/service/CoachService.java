package org.aston.service;

import org.aston.dto.request.CoachRequestDTO;
import org.aston.dto.response.CoachResponseDTO;
import org.aston.dto.update.CoachUpdateDTO;
import org.aston.entity.Coach;

import java.util.Set;

public interface CoachService {
    CoachResponseDTO find(Long id);

    void save(CoachRequestDTO dto);

    void update(CoachUpdateDTO dto);

    void delete(Long id);

    Set<Coach> findCustomerCoaches(Long customerId);
}
