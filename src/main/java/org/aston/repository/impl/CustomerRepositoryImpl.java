package org.aston.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.aston.entity.Customer;
import org.aston.exception.EntityNotFoundException;
import org.aston.repository.CustomerRepository;
import org.aston.util.HikariCPDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public Customer find(Long customerId) {
        Customer customer;
        String sql = "select * from customer where id = ?";

        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            customer = mapRowToCustomer(resultSet);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new EntityNotFoundException("Строка в БД с id=" + customerId + " не найдена");
        }
        return customer;
    }

    @Override
    public void save(Customer customer) {
        String sql = "insert into customer(customer_name) values (?)";

        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, customer.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Customer customer) {
        String sql = "update customer set customer_name=? where id=?";

        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, customer.getName());
            preparedStatement.setLong(2, customer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Long customerId) {
        String sql = "delete from customer where id=?";

        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, customerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void assignCoach(Long customerId, Long coachId) {
        String sql = "insert into customer_coach(customer_id, coach_id) values (?,?)";

        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, customerId);
            preparedStatement.setLong(2, coachId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Customer> findCoachCustomers(Long coachId) {
        Set<Customer> customers = new HashSet<>();
        String sql = "select * " +
                "from customer " +
                "where id in (select customer_id " +
                "from customer_coach " +
                "where coach_id=?)";
        try (Connection connection = HikariCPDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, coachId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customers.add(mapRowToCustomer(resultSet));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return customers;
    }

    private Customer mapRowToCustomer(ResultSet resultSet) throws SQLException {
        return Customer.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("customer_name"))
                .build();
    }
}
