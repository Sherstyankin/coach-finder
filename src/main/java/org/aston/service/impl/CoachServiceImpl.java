package org.aston.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.aston.dto.request.CoachRequestDTO;
import org.aston.dto.response.CoachResponseDTO;
import org.aston.dto.response.GymResponseDTO;
import org.aston.dto.update.CoachUpdateDTO;
import org.aston.entity.Coach;
import org.aston.mapper.CoachMapper;
import org.aston.repository.CoachRepository;
import org.aston.repository.impl.CoachRepositoryImpl;
import org.aston.service.CoachService;
import org.aston.service.GymService;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
public class CoachServiceImpl implements CoachService {
    private CoachRepository coachRepository = new CoachRepositoryImpl();
    private GymService gymService = new GymServiceImpl();

    @Override
    public CoachResponseDTO find(Long coachId) {
        Coach coach = coachRepository.find(coachId);
        GymResponseDTO gymDTO = gymService.find(coach.getGymId());
        return CoachMapper.toDTO(coach, gymDTO);
    }

    @Override
    public void save(CoachRequestDTO dto) {
        Coach coach = CoachMapper.toEntity(dto);
        coachRepository.save(coach);
    }

    @Override
    public void update(CoachUpdateDTO dto) {
        Coach coach = CoachMapper.toEntity(dto);
        coachRepository.update(coach);
    }

    @Override
    public void delete(Long coachId) {
        coachRepository.delete(coachId);
    }

    @Override
    public Set<Coach> findCustomerCoaches(Long customerId) {
        return coachRepository.findCustomerCoaches(customerId);

    }
}
