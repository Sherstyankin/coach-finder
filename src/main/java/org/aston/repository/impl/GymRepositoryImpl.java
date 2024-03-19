package org.aston.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.aston.entity.Gym;
import org.aston.exception.EntityNotFoundException;
import org.aston.repository.GymRepository;
import org.aston.util.HikariCPDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class GymRepositoryImpl implements GymRepository {
    @Override
    public Gym find(Long gymId) {
        Gym gym;
        String sql = "select * from gym where id = ?";

        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, gymId);
            ResultSet resultSet = preparedStatement.executeQuery();
            gym = mapRowToGym(resultSet);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return gym;
    }

    @Override
    public void save(Gym gym) {
        String sql = "insert into gym(gym_name, city) values (?,?)";

        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, gym.getName());
            preparedStatement.setString(2, gym.getCity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Gym gym) {
        String sql = "update gym set gym_name=?, city=? where id=?";

        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, gym.getName());
            preparedStatement.setString(2, gym.getCity());
            preparedStatement.setLong(3, gym.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long gymId) {
        String sql = "delete from gym where id=?";

        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, gymId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private Gym mapRowToGym(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return Gym.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("gym_name"))
                    .city(resultSet.getString("city"))
                    .build();
        } else {
            log.error("Объект в БД не найден");
            throw new EntityNotFoundException("Объект в БД не найден");
        }
    }
}
