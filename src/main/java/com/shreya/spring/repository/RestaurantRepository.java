package com.shreya.spring.repository;

import com.shreya.spring.model.Customer;
import com.shreya.spring.model.Restaurant;
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

public class RestaurantRepository {
    private static Connection connection = null;
    Set<Restaurant> restaurants = new HashSet<>();

    private void initConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = new ConnectionService().getConnection();
        }
    }

    public void addRestaurant(Restaurant restaurant) throws SQLException {
        this.initConnection();
        String query = "insert into restaurant values (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, restaurant.getRegisterNo());
            preparedStatement.setString(2, restaurant.getName());
            preparedStatement.setString(3, restaurant.getCity());
            preparedStatement.setString(4, restaurant.getArea());

            System.out.println("inserting restaurant data to table: " + restaurant);

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

    public List<Restaurant> retrieveRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        String query = "SELECT * FROM restaurant";

        try {
            this.initConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int registerNo = resultSet.getInt("registerNo");
                String name = resultSet.getString("name");
                String city = resultSet.getString("city");
                String area = resultSet.getString("area");

                Restaurant restaurant = new Restaurant(registerNo, name, city, area);
                restaurants.add(restaurant);
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
        return restaurants;
    }

    public void retrieveRestaurant(int registerNo, String name) {
        Restaurant restaurant = null;
        String sql = "SELECT * FROM restaurant WHERE id = ? AND name = ?";

        try {
            this.initConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, registerNo);
            preparedStatement.setString(2, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String city = resultSet.getString("city");
                String area = resultSet.getString("area");

                restaurant = new Restaurant(registerNo, name, city, area);
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
    }

    public boolean deleteRestaurant(int registerNo) throws SQLException {

        try {
            this.initConnection();
            String query = "DELETE FROM restaurant WHERE registerNo = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, registerNo);
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

    public boolean updateRestaurant(int registerNo, String name) throws SQLException {
        Customer customer = null;
        try {
            this.initConnection();
            Statement statement = connection.createStatement();
            String sql = "UPDATE restaurant SET name = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, registerNo);

                return preparedStatement.executeUpdate() > 0;
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

    public void createRestaurant(Restaurant restaurant) {

        restaurants.add(restaurant);
    }

    public void displayRestaurant(Restaurant restaurant) {

        restaurants.remove(restaurant);
    }

    public void displayRestaurantToBeClosed(String name) {
        Restaurant restaurantToBeClosed = null;
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName() == name) {
                restaurantToBeClosed = restaurant;
            }
        }
        restaurants.remove(restaurantToBeClosed);
    }
}
