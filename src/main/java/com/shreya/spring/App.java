package com.shreya.spring;

import com.shreya.spring.controller.CustomerController;
import com.shreya.spring.controller.DeliveryAgentController;
import com.shreya.spring.controller.OrderController;
import com.shreya.spring.controller.RestaurantController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class App {

    public static ApplicationContext context;

    public static void main(String[] args) {
        System.out.println("Hello World!");

        context = new ClassPathXmlApplicationContext("spring.xml");
        CustomerController customerController = context.getBean("customerController", CustomerController.class);

        customerController.run();

        DeliveryAgentController deliveryAgentController = context.getBean("deliveryAgentController", DeliveryAgentController.class);
        deliveryAgentController.run();

        OrderController orderController = context.getBean("orderController", OrderController.class);
        orderController.run();

        RestaurantController restaurantController = context.getBean("restaurantController", RestaurantController.class);
        restaurantController.run();


    }
}
