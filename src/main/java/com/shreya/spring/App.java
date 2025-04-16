package com.shreya.spring;

import com.shreya.spring.controller.CustomerController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        System.out.println("Starting Spring Application with XML config...");

        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        CustomerController controller = context.getBean("customerController", CustomerController.class);
        controller.run();
    }
}
