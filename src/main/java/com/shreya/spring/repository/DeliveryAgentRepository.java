package com.shreya.spring.repository;

import com.shreya.spring.model.DeliveryAgent;
import com.shreya.spring.service.ConnectionService;
import lombok.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class DeliveryAgentRepository {
    private static Connection connection = null;
    Set<DeliveryAgent> deliveryAgents = new HashSet<>();

    private void initConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = new ConnectionService().getConnection();
        }
    }

    public boolean insertDeliveryAgent(DeliveryAgent deliveryAgent) throws SQLException {
        this.initConnection();
        String query = "insert into deliveryAgent values (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, deliveryAgent.getId());
            preparedStatement.setString(2, deliveryAgent.getName());
            preparedStatement.setString(3, deliveryAgent.getCity());
            preparedStatement.setInt(4, deliveryAgent.getMobileNo());
            System.out.println("inserting deliveryAgent data to table: " + deliveryAgent);

            int rowsInserted = preparedStatement.executeUpdate();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("connection is closed: " + e.getMessage());

                }
            }
        }
        return false;
    }

    public boolean deleteDeliveryAgent(int id) throws SQLException {
        String sql = "DELETE FROM DeliveryAgent WHERE id = ?";

        try {
            this.initConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
            throw new RuntimeException(e);  // Re-throw exception
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

    public List<DeliveryAgent> retrieveDeliveryAgents() {
        List<DeliveryAgent> deliveryAgents = new ArrayList<>();
        String sql = "SELECT * FROM deliveryagent";

        try {
            this.initConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String city = resultSet.getString("city");
                int mobileNo = resultSet.getInt("mobileNo");

                DeliveryAgent deliveryAgent = new DeliveryAgent(id, name, city, mobileNo);
                deliveryAgents.add(deliveryAgent);
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
        return deliveryAgents;
    }

    public void retrieveDeliveryAgent(int id, String name) {
        DeliveryAgent deliveryAgent = null;
        String sql = "SELECT * FROM deliveryagent WHERE id = ? AND name = ?";

        try {
            this.initConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String city = resultSet.getString("city");
                int mobileNo = resultSet.getInt("mobileNo");

                deliveryAgent = new DeliveryAgent(id, name, city, mobileNo);
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

    public void createDeliveryAgent(DeliveryAgent deliveryAgent) {

        deliveryAgents.add(deliveryAgent);
    }

    public void displayDeliveryAgent(DeliveryAgent deliveryAgent) {

        deliveryAgents.remove(deliveryAgent);
    }

    public void displayDeliveryAgentToBeClosed(int id) {

        DeliveryAgent deliveryAgentToBeClosed = null;
        for (DeliveryAgent deliveryAgent : deliveryAgents) {
            if (deliveryAgent.getId() == id) {
                deliveryAgentToBeClosed = deliveryAgent;
            }
        }
        deliveryAgents.remove(deliveryAgentToBeClosed);
    }
}