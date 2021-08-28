package com.geekbrains.webapp.controllers;

import com.geekbrains.webapp.services.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CustomerController {
    private CustomerDAO customerDAO;

    @Autowired
    public CustomerController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @GetMapping("/customers")
    public String showAllCustomers(Model model) {
        model.addAttribute("customers", customerDAO.findAll());
        return "customers";
    }

    @GetMapping("/customer/orders/{id}")
    public String showOrders(Model model, @PathVariable Long id) {
        model.addAttribute("customer", customerDAO.findByIdWithOrders(id));
        return "customer_order_info";
    }


}
