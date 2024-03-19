package org.aston.service.impl;

import org.aston.dto.request.CoachRequestDTO;
import org.aston.dto.response.CoachResponseDTO;
import org.aston.dto.update.CoachUpdateDTO;
import org.aston.entity.Coach;
import org.aston.repository.CoachRepository;
import org.aston.repository.impl.CoachRepositoryImpl;
import org.aston.service.CoachService;
import org.aston.service.GymService;
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

class CoachServiceImplTest {

    private final CoachRepository mockCoachRepository = Mockito.mock(CoachRepositoryImpl.class);
    private final GymService gymService = Mockito.mock(GymServiceImpl.class);
    private final CoachService coachService = new CoachServiceImpl(mockCoachRepository, gymService);
    private CoachRequestDTO requestDTO;
    private CoachResponseDTO responseDTO;
    private CoachUpdateDTO updateDTO;
    private Coach coach;
    private Coach coachToSave;
    private Coach coachToUpdate;

    @BeforeEach
    void setUp() {
        requestDTO = CoachRequestDTO.builder()
                .name("Sergey")
                .sportType("Cycling")
                .gymId(1L)
                .build();
        responseDTO = CoachResponseDTO.builder()
                .id(1L)
                .name("Sergey")
                .sportType("Cycling")
                .gym(null)
                .customers(Collections.emptySet())
                .build();
        updateDTO = CoachUpdateDTO.builder()
                .id(1L)
                .name("Andrey")
                .sportType("Boxing")
                .gymId(1L)
                .build();
        coach = Coach.builder()
                .id(1L)
                .name("Sergey")
                .sportType("Cycling")
                .gymId(1L)
                .build();
        coachToSave = Coach.builder()
                .name("Sergey")
                .sportType("Cycling")
                .gymId(1L)
                .build();
        coachToUpdate = Coach.builder()
                .id(1L)
                .name("Andrey")
                .sportType("Boxing")
                .gymId(1L)
                .build();
    }

    @AfterEach
    void tearDown() {
        requestDTO = null;
        responseDTO = null;
        updateDTO = null;
        coach = null;
        coachToSave = null;
        coachToUpdate = null;
    }

    @ParameterizedTest
    @ValueSource(longs = {1, Long.MAX_VALUE})
    void findCoachByIdThenReturnDTO(Long id) {
        Mockito
                .when(mockCoachRepository.find(id))
                .thenReturn(coach);

        CoachResponseDTO result = coachService.find(id);
        Assertions.assertEquals(responseDTO, result);
    }

    @Test
    void saveCoach() {
        coachService.save(requestDTO);
        verify(mockCoachRepository, times(1)).save(coachToSave);
    }

    @Test
    void updateCoach() {
        coachService.update(updateDTO);
        verify(mockCoachRepository, times(1)).update(coachToUpdate);
    }

    @Test
    void delete() {
        coachService.delete(1L);
        verify(mockCoachRepository, times(1)).delete(1L);
    }

    @Test
    void findCustomerCoaches() {
        Set<Coach> customers = new HashSet<>();
        customers.add(coach);
        Mockito
                .when(mockCoachRepository.findCustomerCoaches(1L))
                .thenReturn(customers);

        Set<Coach> result = coachService.findCustomerCoaches(1L);
        Assertions.assertEquals(customers, result);
    }
}