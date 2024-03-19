package org.aston.dto.response;

import lombok.*;
import org.aston.entity.Customer;

import java.util.Objects;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoachResponseDTO {
    private Long id;
    private String name;
    private String sportType;
    private GymResponseDTO gym;
    private Set<Customer> customers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoachResponseDTO that = (CoachResponseDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(sportType, that.sportType) && Objects.equals(gym, that.gym);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sportType, gym);
    }
}
