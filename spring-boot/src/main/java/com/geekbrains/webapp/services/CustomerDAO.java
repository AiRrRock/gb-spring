package com.geekbrains.webapp.services;

import com.geekbrains.webapp.model.Customer;
import com.geekbrains.webapp.model.Order;
import com.geekbrains.webapp.model.Product;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.Collections;
import java.util.List;

@Service
public class CustomerDAO {
    private SessionService service;

    @Autowired
    public CustomerDAO(SessionService service) {
        this.service = service;
    }


    public List<Customer> findAll() {
        try (Session session = service.getFactory().getCurrentSession()) {
            session.beginTransaction();
            List<Customer> customers = session.createQuery("from Customer").getResultList();
            session.getTransaction().commit();
            return Collections.unmodifiableList(customers);
        }
    }

    public Customer findById(Long id) {
        try (Session session = service.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Customer customer = session.find(Customer.class, id);
            customer.setOrders(Collections.emptyList());
            return customer;
        }
    }

    public Customer findByIdWithOrders(Long id) {
        try (Session session = service.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Customer customer = session
                    .createNamedQuery("withOrders", Customer.class)
                    .setParameter("id", id)
                    .getSingleResult();
            for (Order o : customer.getOrders()) {
                o.getProduct();
            }
            return customer;
        } catch (NoResultException e) {
            return findById(id);
        }
    }

    public void saveOrUpdate(Customer customer) {
        try (Session session = service.getFactory().getCurrentSession()) {
            session.beginTransaction();
            session.merge(customer);
            session.flush();
            session.getTransaction().commit();
        }
    }

    public void deleteById(Long id) {
        try (Session session = service.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.delete(product);
            session.getTransaction().commit();
        }
    }

}
