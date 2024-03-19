package org.aston.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.aston.entity.Coach;
import org.aston.exception.EntityNotFoundException;
import org.aston.repository.CoachRepository;
import org.aston.util.HikariCPDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class CoachRepositoryImpl implements CoachRepository {
    @Override
    public Coach find(Long coachId) {
        Coach coach;
        String sql = "select * from coach where id = ?";

        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, coachId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            coach = mapRowToCoach(resultSet);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new EntityNotFoundException("Строка в БД с id=" + coachId + " не найдена");
        }
        return coach;
    }

    @Override
    public void save(Coach coach) {
        String sql = "insert into coach(coach_name, sport_type, gym_id) values (?,?,?)";

        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, coach.getName());
            preparedStatement.setString(2, coach.getSportType());
            preparedStatement.setLong(3, coach.getGymId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Coach coach) {
        String sql = "update coach set coach_name=?, sport_type=?, gym_id=?  where id=?";

        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, coach.getName());
            preparedStatement.setString(2, coach.getSportType());
            preparedStatement.setLong(3, coach.getGymId());
            preparedStatement.setLong(4, coach.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long coachId) {
        String sql = "delete from coach where id=?";

        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, coachId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Coach> findCustomerCoaches(Long customerId) {
        Set<Coach> coaches = new HashSet<>();
        String sql = "select * " +
                "from coach " +
                "where id in (select coach_id " +
                "from customer_coach " +
                "where customer_id=?)";
        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                coaches.add(mapRowToCoach(resultSet));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return coaches;
    }

    private Coach mapRowToCoach(ResultSet resultSet) throws SQLException {
        return Coach.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("coach_name"))
                .sportType(resultSet.getString("sport_type"))
                .gymId(resultSet.getLong("gym_id"))
                .build();
    }
}
