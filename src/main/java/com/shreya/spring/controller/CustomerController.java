package com.shreya.spring.controller;

import com.shreya.spring.exception.CustomerException;
import com.shreya.spring.exception.CustomerNotfound;
import com.shreya.spring.model.Customer;
import com.shreya.spring.repository.CustomerRepository;
import com.shreya.spring.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.SQLException;
import java.util.Scanner;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class CustomerController {

    private final Scanner sc = new Scanner(System.in);
    CustomerRepository customerRepository = new CustomerRepository();
    Customer customer = new Customer();
    private CustomerService customerService;

    public CustomerController(CustomerService customerService, CustomerRepository customerRepository) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }

    public void run() {

        int option;
        do {
            System.out.println("\n---- Customer ----");
            System.out.println("1. Add Customer");
            System.out.println("2. View Customer Details");
            System.out.println("3. create Customer on db");
            System.out.println("4. delete Customer on db");
            System.out.println("5. Retrieve Customer");
            System.out.println("6. Update Customer");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");

            option = Integer.parseInt(sc.nextLine());
            try {
                switch (option) {
                    case 1:
                        customerService.createCustomer();
                        customerService.displayCustomers();
                        customerService.displayCustomerInfo();
                        customerRepository.createCustomer(customer);
                        customerRepository.displayCustomers(customer);
                        System.out.println("Customer created : " + customer);
                        break;
                    case 2:
                        customerService.displayCustomers();
                        break;
                    case 3:
                        System.out.println("Performing create operation on Customer");
                        CustomerService.insertCustomer(new Customer(101, "ABC", "pune", 908547358, 22) {

                        });
                        break;
                    case 4:
                        System.out.println("delete Customer");
                        CustomerService.deleteCustomer();
                        break;
                    case 5:
                        System.out.println("Retrieve Customer");
                        customerService.retrieveCustomers().forEach(customer -> {
                            System.out.println("customer ID: " + customer.getId() + ", name: " + customer.getName());
                        });
                        break;
                    case 6:
                        System.out.println("Update Customer");
                        CustomerService.updateCustomer();
                        break;

                    case 0:
                        System.out.println("Returning to Main Menu...");
                        break;
                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            } catch (CustomerException e) {
                System.out.println("Error: " + e.getClass());
            } catch (CustomerNotfound | SQLException e) {
                throw new RuntimeException(e);
            }
        } while (option != 0);
    }
}