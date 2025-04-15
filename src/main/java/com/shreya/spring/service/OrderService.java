package com.shreya.spring.service;

import com.shreya.spring.model.Order;
import com.shreya.spring.repository.OrderRepository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class OrderService implements OrderNumberService {

    static OrderRepository orderRepository = new OrderRepository();
    HashMap<Integer, Order> orders = new HashMap<>();
    Scanner sc = new Scanner(System.in);

    public static void insertOrder(Order order) throws SQLException {
        orderRepository.addOrder(order);
    }

    public static void Order(Order order) throws SQLException {

        orderRepository.retrieveOrder(1, "abc");
    }

    public static void deleteOrder() {

        try {
            if (orderRepository.deleteOrder(1)) {
                System.out.println("Order deleted successfully!");
            } else {
                System.out.println("Failed to delete Order.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateOrder() throws SQLException {
        if (orderRepository.updateOrder(2, "meal")) {
            System.out.println("Order updated successfully ");
        } else {
            System.out.println("Failed to update Order");
        }

    }

    public static void deleteOrder(int orderId) {
        String removeOrder = String.valueOf(orderId);
        System.out.println("remove order " + removeOrder);
    }

    public List<Order> retrieveOrders() {
        return orderRepository.retrieveOrders();
    }

    void printCustomer(Order order) {
        System.out.println(order);
    }

    public void createOrder() {
        Order order = new Order();
        orderRepository.createOrder(order);
        orderRepository.displayOrder(order);
        orderRepository.displayOrderToBeClosed(1);

        try {
            System.out.println("Please enter id");
            int id = Integer.parseInt(sc.nextLine());

            System.out.println("Please enter type");
            String type = sc.nextLine();

            System.out.println("Please enter note");
            String note = sc.nextLine();

            System.out.println("Please enter paymentMethod");
            String paymentMethod = sc.nextLine();

            order.setId(id);
            order.setPaymentMethod(paymentMethod);
            order.setNote(note);
            order.setType(type);
            orders.put(1, order);
        } catch (Exception e) {
            System.out.println("Invalid input type correct data");
        }
    }

    @Override
    public void createOrderNo() {
    }

    public void displayOrder() {
        try {
//            Set<Map.Entry<Integer, Order>> entrySet = orders.entrySet();
//            for (Map.Entry<Integer, Order> customerEntry : entrySet) {
//                System.out.println("Customer Info: " + orders);
//            }

            //java 8 features forEach loop..
            orders.forEach((Id, orders) -> System.out.println("orderId " + Id + " = order info " + orders));

        } catch (Exception e) {
            System.out.println("Invalid input type correct data");
        }

    }
}
