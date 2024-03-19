package org.aston.service;

import org.aston.dto.request.GymRequestDTO;
import org.aston.dto.response.GymResponseDTO;
import org.aston.dto.update.GymUpdateDTO;

public interface GymService {
    GymResponseDTO find(Long gymId);

    void save(GymRequestDTO dto);

    void update(GymUpdateDTO dto);

    void delete(Long gymId);
}
