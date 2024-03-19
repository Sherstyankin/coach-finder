package org.aston.repository;

import org.aston.entity.Coach;

import java.util.Set;

public interface CoachRepository {
    Coach find(Long coachId);

    void save(Coach coach);

    void update(Coach coach);

    void delete(Long coachId);

    Set<Coach> findCustomerCoaches(Long customerId);
}
