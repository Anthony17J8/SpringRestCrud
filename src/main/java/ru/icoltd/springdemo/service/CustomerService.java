package ru.icoltd.springdemo.service;

import java.util.List;

import ru.icoltd.springdemo.entity.Customer;

public interface CustomerService {

    List<Customer> getCustomers();

    void saveCustomer(Customer theCustomer);

    Customer getCustomer(int theId);

    void deleteCustomer(int theId);
}
