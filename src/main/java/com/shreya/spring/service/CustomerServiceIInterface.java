package com.shreya.spring.service;

import com.shreya.spring.exception.InvalideCustomerIDException;
import com.shreya.spring.exception.InvalideCustomerNameException;
import com.shreya.spring.model.Customer;

import java.sql.SQLException;

public interface CustomerServiceIInterface {
    void createCustomer() throws InvalideCustomerIDException, InvalideCustomerNameException, SQLException;

    void displayCustomers();

    Customer getCustomerById(int id);

}
