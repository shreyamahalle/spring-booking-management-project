package com.shreya.spring.repository;
import com.shreya.spring.model.Order;
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

public class OrderRepository {
    private static Connection connection = null;
    Set<Order> orders = new HashSet<>();


    private void initConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = new ConnectionService().getConnection();
        }
    }

    public void addOrder(Order order) throws SQLException {
        this.initConnection();
        String query = "insert into orderr values (?, ?, ?, ?)";
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, order.getId());
                preparedStatement.setString(2, order.getType());
                preparedStatement.setString(3, order.getNote());
                preparedStatement.setString(4, order.getPaymentMethod());
                System.out.println("inserting order data to table: " + order);

            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
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

    public List<Order> retrieveOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orderr";

        try {
            this.initConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String type = resultSet.getString("type");
                String note = resultSet.getString("note");
                String paymentMethod = resultSet.getString("paymentMethod");

                Order order = new Order(id, type, note, paymentMethod);
                orders.add(order);
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
        return orders;
    }

    public void retrieveOrder(int id, String type) {
        Order order = null;
        String sql = "SELECT * FROM orderr WHERE id = ? AND type = ?";

        try {
            this.initConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, type);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String note = resultSet.getString("note");
                String paymentMethod = resultSet.getString("paymentMethod");
                order = new Order(id, type, note, paymentMethod);
            }

        } catch (RuntimeException | SQLException e) {
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

    public boolean deleteOrder(int id) throws SQLException {
        String sql = "DELETE FROM orderr WHERE id = ?";

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

    public boolean updateOrder(int id, String type) throws SQLException {
        Order order = null;
        try {
            this.initConnection();
            Statement statement = connection.createStatement();
            String query = "UPDATE orderr SET type = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, type);
                preparedStatement.setInt(2, id);
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

    public void createOrder(Order order) {

        orders.add(order);
    }

    public void displayOrder(Order order) {
        orders.remove(order);
    }

    public void displayOrderToBeClosed(int id) {
        Order orderToBeClosed = null;
        for (Order order : orders) {
            if (order.getId() == id) {
                orderToBeClosed = order;
            }
        }
        orders.remove(orderToBeClosed);
    }

}
