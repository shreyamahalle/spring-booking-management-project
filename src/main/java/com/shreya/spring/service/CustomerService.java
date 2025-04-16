package com.shreya.spring.service;

import com.shreya.spring.exception.CustomerNotfound;
import com.shreya.spring.model.Customer;
import com.shreya.spring.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerService {

    private static CustomerRepository customerRepository = new CustomerRepository();
    private final Map<Integer, Customer> customers = new HashMap<>();
    Scanner sc = new Scanner(System.in);

    public static void insertCustomer(Customer customer) throws SQLException {
        customerRepository.addCustomer(customer);
    }

    public static void Customer(Customer customer) {

        customerRepository.retrieveCustomer(1, "abc");
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
        this.customerRepository = customerRepository;
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
        Customer customer = new Customer() {

        };
        customerRepository.createCustomer(customer);
        customerRepository.displayCustomers(customer);
        customerRepository.displayCustomerToBeClosed(1);

        try {
            System.out.println("Please enter id:");
            int id = Integer.parseInt(sc.nextLine());

            System.out.println("Please enter name:");
            String name = sc.nextLine();

            System.out.println("Please enter city:");
            String city = sc.nextLine();

            System.out.println("Please enter mobile number:");
            int mobileNo = Integer.parseInt(sc.nextLine());

            System.out.println("Please enter age:");
            int age = Integer.parseInt(sc.nextLine());

            customer.setId(id);
            customer.setAge(age);
            customer.setCity(city);
            customer.setName(name);
            customer.setMobileNo(mobileNo);
            customers.put(1, customer);

        } catch (Exception e) {
            System.out.println("Invalid input type correct data");
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
