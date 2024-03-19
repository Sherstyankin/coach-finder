package org.aston.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.aston.dto.request.GymRequestDTO;
import org.aston.dto.response.GymResponseDTO;
import org.aston.dto.update.GymUpdateDTO;
import org.aston.entity.Gym;
import org.aston.mapper.GymMapper;
import org.aston.repository.GymRepository;
import org.aston.repository.impl.GymRepositoryImpl;
import org.aston.service.GymService;

@AllArgsConstructor
@NoArgsConstructor
public class GymServiceImpl implements GymService {

    private GymRepository gymRepository = new GymRepositoryImpl();

    @Override
    public GymResponseDTO find(Long gymId) {
        Gym gym = gymRepository.find(gymId);
        return GymMapper.toDTO(gym);
    }

    @Override
    public void save(GymRequestDTO dto) {
        Gym gym = GymMapper.toEntity(dto);
        gymRepository.save(gym);
    }

    @Override
    public void update(GymUpdateDTO dto) {
        Gym gym = GymMapper.toEntity(dto);
        gymRepository.update(gym);
    }

    @Override
    public void delete(Long gymId) {
        gymRepository.delete(gymId);
    }
}
