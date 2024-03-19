package org.aston.repository;

import org.aston.entity.Gym;

public interface GymRepository {
    Gym find(Long gymId);

    void save(Gym gym);

    void update(Gym gym);

    void delete(Long gymId);
}
