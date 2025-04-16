package com.shreya.spring.repository;

import com.shreya.spring.model.Customer;
import com.shreya.spring.service.ConnectionService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
public class CustomerRepository {

    private static Connection connection = null;
    Set<Customer> customers = new HashSet<>();

    private void initConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = new ConnectionService().getConnection();
        }
    }

    public void addCustomer(Customer customer) throws SQLException {
        this.initConnection();
        String query = "insert into customer values (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getCity());
            preparedStatement.setInt(4, Integer.parseInt(customer.getMobileNo()));
            preparedStatement.setInt(4, customer.getAge());
            System.out.println("inserting customer data to table: " + customer);


        } catch (RuntimeException e) {
            throw new RuntimeException(e);

        } finally { //close connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }
    }

    public List<Customer> retrieveCustomers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customer";

        try {
            this.initConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String city = resultSet.getString("city");
                int mobileNo = resultSet.getInt("mobileNo");
                int age = resultSet.getInt("age");

                Customer customer = new Customer(id, name, city, mobileNo, age) {

                };
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        return customers;
    }

    public Customer retrieveCustomer(int id, String name) {
        Customer customer = null;
        String sql = "SELECT * FROM customer WHERE id = ? AND name = ?";

        try {
            this.initConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String city = resultSet.getString("city");
                int mobileNo = resultSet.getInt("mobileNo");
                int age = resultSet.getInt("age");
                customer = new Customer(id, name, city, mobileNo, age) {

                };
            }
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        return customer;
    }

    public boolean deleteCustomer(int id) throws SQLException {
        String sql = "DELETE FROM Customer WHERE id = ?";

        try {
            this.initConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("connection is closed: " + e.getMessage());
                }
            }
        }
    }

    public boolean updateCustomer(int id, String name) throws SQLException {
        Customer customer = null;
        try {
            this.initConnection();
            Statement statement = connection.createStatement();
            String sql = "UPDATE Customer SET name = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Set the parameters for the prepared statement
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, id);

                // Execute the update query and return true if the update was successful
                return preparedStatement.executeUpdate() > 0;  // Returns true if at least one row was updated
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }
    }

    public void createCustomer(Customer customer) {

        customers.add(customer);
    }

    public void displayCustomers(Customer customer) {
        customers.remove(customer);
    }

    public void displayCustomerToBeClosed(int id) {
        Customer customerToBeClosed = null;
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                customerToBeClosed = customer;
            }
        }
        customers.remove(customerToBeClosed);
    }

}