package org.aston.service.impl;

import org.aston.dto.request.GymRequestDTO;
import org.aston.dto.response.GymResponseDTO;
import org.aston.dto.update.GymUpdateDTO;
import org.aston.entity.Gym;
import org.aston.repository.GymRepository;
import org.aston.repository.impl.GymRepositoryImpl;
import org.aston.service.GymService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class GymServiceImplTest {

    private final GymRepository mockGymRepository = Mockito.mock(GymRepositoryImpl.class);
    private final GymService gymService = new GymServiceImpl(mockGymRepository);
    private GymRequestDTO requestDTO;
    private GymResponseDTO responseDTO;
    private GymUpdateDTO updateDTO;
    private Gym gym;
    private Gym gymToSave;
    private Gym gymToUpdate;

    @BeforeEach
    void setUp() {
        requestDTO = GymRequestDTO.builder()
                .name("Gym Pro")
                .city("Moscow")
                .build();
        responseDTO = GymResponseDTO.builder()
                .id(1L)
                .name("Gym Pro")
                .city("Moscow")
                .build();
        updateDTO = GymUpdateDTO.builder()
                .id(1L)
                .name("Gym Pro")
                .city("Samara")
                .build();
        gym = Gym.builder()
                .id(1L)
                .name("Gym Pro")
                .city("Moscow")
                .build();
        gymToSave = Gym.builder()
                .name("Gym Pro")
                .city("Moscow")
                .build();
        gymToUpdate = Gym.builder()
                .id(1L)
                .name("Gym Pro")
                .city("Samara")
                .build();
    }

    @AfterEach
    void tearDown() {
        requestDTO = null;
        responseDTO = null;
        updateDTO = null;
        gym = null;
        gymToSave = null;
        gymToUpdate = null;
    }

    @ParameterizedTest
    @ValueSource(longs = {1, Long.MAX_VALUE})
    void findCoachByIdThenReturnDTO(Long id) {
        Mockito
                .when(mockGymRepository.find(id))
                .thenReturn(gym);

        GymResponseDTO result = gymService.find(id);
        Assertions.assertEquals(responseDTO, result);
    }

    @Test
    void saveCoach() {
        gymService.save(requestDTO);
        verify(mockGymRepository, times(1)).save(gymToSave);
    }

    @Test
    void updateCoach() {
        gymService.update(updateDTO);
        verify(mockGymRepository, times(1)).update(gymToUpdate);
    }

    @Test
    void delete() {
        gymService.delete(1L);
        verify(mockGymRepository, times(1)).delete(1L);
    }
}