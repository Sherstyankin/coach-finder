package org.aston.mapper;

import lombok.experimental.UtilityClass;
import org.aston.dto.request.GymRequestDTO;
import org.aston.dto.response.GymResponseDTO;
import org.aston.dto.update.GymUpdateDTO;
import org.aston.entity.Gym;

@UtilityClass
public class GymMapper {
    public Gym toEntity(GymRequestDTO dto) {
        return Gym.builder()
                .name(dto.getName())
                .city(dto.getCity())
                .build();
    }

    public Gym toEntity(GymUpdateDTO dto) {
        return Gym.builder()
                .id(dto.getId())
                .name(dto.getName())
                .city(dto.getCity())
                .build();
    }

    public GymResponseDTO toDTO(Gym gym) {
        return GymResponseDTO.builder()
                .id(gym.getId())
                .name(gym.getName())
                .city(gym.getCity())
                .build();
    }
}
