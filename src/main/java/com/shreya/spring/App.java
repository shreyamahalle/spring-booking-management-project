package com.shreya.spring;

import com.shreya.spring.controller.*;
import com.shreya.spring.exception.InvalideCustomerIDException;
import com.shreya.spring.impl.CustomerImpl;
import com.shreya.spring.impl.OrderNumberImpl;
import com.shreya.spring.model.Customer;
import com.shreya.spring.model.DeliveryAgent;
import com.shreya.spring.model.Order;
import com.shreya.spring.model.Restaurant;
import com.shreya.spring.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.Scanner;


public class App {

    private static final ConnectionService connectionService = new ConnectionService();
    private static final Scanner sc = new Scanner(System.in);
    public static ApplicationContext context;

    public static void main(String[] args) throws InvalideCustomerIDException, SQLException {
        context = new ClassPathXmlApplicationContext("spring.xml");

        int option = 0;
        do {
            System.out.println("-----------Booking Management-----------");
            System.out.println("Please choose the option");
            System.out.println("1. Create customer");
            System.out.println("2. Create delivery agent");
            System.out.println("3. Create order");
            System.out.println("4. Create restaurant ");
            System.out.println("5. Display Order Number");
            System.out.println("6. Combo Pack Offer");
            System.out.println("0. Exit project");
            System.out.println("Select the option..");
            option = Integer.parseInt(sc.nextLine());

            switch (option) {
                case 1:
                    CustomerController customerController = context.getBean("customerController", CustomerController.class);
                    customerController.run();
                    Customer customer = new Customer();
                    System.out.println("Customer created : " + customer);
                    break;
            }
            switch (option) {
                case 2:
                    DeliveryAgentController deliveryAgentController = context.getBean("deliveryAgentController", DeliveryAgentController.class);
                    deliveryAgentController.run();
                    DeliveryAgent deliveryAgent = new DeliveryAgent();
                    System.out.println("DeliveryAgent created : " + deliveryAgent);
                    break;
            }
            switch (option) {
                case 3:
                    OrderController orderController = context.getBean("orderController", OrderController.class);
                    OrderService orderService = new OrderService();
                    OrderNumberService orderNumberService = new OrderNumberImpl();
                    orderController.run();
                    orderService.createOrder();
                    Order order = new Order();
                    orderNumberService.createOrderNo();
                    System.out.println("Order created : " + order);
                    break;
            }
            switch (option) {
                case 4:
                    RestaurantController restaurantController = context.getBean("restaurantController", RestaurantController.class);
                    restaurantController.run();
                    Restaurant restaurant = new Restaurant();
                    System.out.println("Restaurant created : " + restaurant);
                    break;
            }
            switch (option) {
                case 5:
                    OrderNumberService orderNumberService = new OrderNumberImpl();
                    CustomerImpl customerImpl = new CustomerImpl();
                    OrderNumberController orderNumberController = new OrderNumberController();
                    orderNumberController.run();
                    orderNumberService.createOrderNo();
                    customerImpl.printCustomer();
                    customerImpl.createOrder();
                    customerImpl.displayOrder();
            }
            switch (option) {
                case 6:
                    OrderMultipleInheritanceService orderMultipleInheritanceService = new OrderMultipleInheritanceService();
                    orderMultipleInheritanceService.displayOrder();
            }
            switch (option) {
                case 0:
                    System.out.println("Exiting project");
                    break;
            }
        }
        while (option != 0);

        System.out.println("THANK YOU!");
    }
}
