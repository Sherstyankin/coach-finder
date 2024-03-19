package org.aston.entity;

import lombok.*;

import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Coach {
    private Long id;
    private String name;
    private String sportType;
    private Long gymId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coach coach = (Coach) o;
        return Objects.equals(id, coach.id) && Objects.equals(name, coach.name) && Objects.equals(sportType, coach.sportType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sportType);
    }
}
