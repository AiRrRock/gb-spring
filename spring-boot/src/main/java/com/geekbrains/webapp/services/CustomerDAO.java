package com.geekbrains.webapp.services;

import com.geekbrains.webapp.model.Customer;
import com.geekbrains.webapp.model.Product;
import com.geekbrains.webapp.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CustomerDAO {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerDAO(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer findByIdWithOrders(Long id) {
        return customerRepository.findByIdWithOrders(id);
    }

    public void saveOrUpdate(Customer customer) {
        customerRepository.saveOrUpdate(customer);
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

}
