package com.shreya.spring.service;

import com.shreya.spring.exception.CustomerNotfound;
import com.shreya.spring.model.Customer;
import com.shreya.spring.model.DeliveryAgent;
import com.shreya.spring.repository.CustomerRepository;
import lombok.Getter;
import lombok.Setter;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
public class CustomerService {

    private static CustomerRepository customerRepository;
    private final Map<Integer, Customer> customers = new HashMap<>();
    private final Scanner sc = new Scanner(System.in);

    public static void insertCustomer() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pls enter customer id:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Pls enter customer name:");
        String  name = scanner.nextLine();
        System.out.println("Pls enter city:");
        String city = scanner.nextLine();
        System.out.println("Pls enter mobileNo :");
        int mobileNo = scanner.nextInt();
        System.out.println("enter age");
        int age = scanner.nextInt();

        Customer customer = new Customer(id, name, city, mobileNo,age);

        try {
            if (customerRepository.addCustomer(customer)) {
                System.out.println("Customer inserted successfully!");
            } else {
                System.out.println("Failed to insert Customer.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void deleteCustomer() {

        try {
            if (customerRepository.deleteCustomer(1)) {
                System.out.println("Customer deleted successfully!");
            } else {
                System.out.println("Failed to delete Customer.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateCustomer() throws SQLException {
        if (customerRepository.updateCustomer(2, "shreya")) {
            System.out.println("Customer updated successfully ");
        } else {
            System.out.println("Failed to update customer");
        }

    }

    public void setCustomerRepository(CustomerRepository customerRepository) {
        CustomerService.customerRepository = customerRepository;
    }

    public List<Customer> retrieveCustomers() {
        return customerRepository.retrieveCustomers();
    }

    public void displayCustomerInfo() throws CustomerNotfound {
        customers.entrySet().stream().parallel()
                .filter(entry -> entry.getValue().getId() > 101)
                .forEach(entry -> System.out.println("Customer ID: " + entry.getKey() + " = Customer Info: " + entry.getValue()));
    }

    public void createCustomer() {
        Customer customer = new Customer();
        customerRepository.createCustomer(customer);
        customerRepository.displayCustomerToBeClosed(1);

        try {
            System.out.println("Please enter customer details:");

            System.out.print("Enter ID: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter City: ");
            String city = sc.nextLine();

            System.out.print("Enter Mobile Number: ");
            int mobileNo = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Age: ");
            int age = Integer.parseInt(sc.nextLine());

            customer.setId(id);
            customer.setName(name);
            customer.setCity(city);
            customer.setMobileNo(mobileNo);
            customer.setAge(age);

            // Insert into DB
            customerRepository.createCustomer(customer);

            System.out.println("Customer created successfully!");
        } catch (Exception e) {
            System.out.println("Invalid input. Please check the data and try again.");
        }
    }
    public void displayCustomers() {
        try {
            //Set<Map.Entry<Integer, Customer>> entrySet = customers.entrySet();
//            for (Map.Entry<Integer, Customer> customerEntry : entrySet) {
//                System.out.println("Customer Info: " + customers);
//            }
            //java 8 features forEach loop ...
            customers.forEach((customerId, customers) -> System.out.println("Customer Id " + customerId + " = Customer Info " + customers));

        } catch (Exception e) {
            System.out.println("Error occurred");
        }

    }
}
