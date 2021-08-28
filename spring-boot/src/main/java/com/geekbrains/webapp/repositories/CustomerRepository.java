package com.geekbrains.webapp.repositories;

import com.geekbrains.webapp.model.Customer;
import com.geekbrains.webapp.model.Order;
import com.geekbrains.webapp.model.Product;
import com.geekbrains.webapp.services.SessionService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import java.util.Collections;
import java.util.List;

@Component

public class CustomerRepository {
    @Autowired
    private SessionService service;

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
            for(Order o : customer.getOrders()){
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
