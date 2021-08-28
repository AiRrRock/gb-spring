package com.geekbrains.webapp.repositories;

import com.geekbrains.webapp.model.Order;
import com.geekbrains.webapp.services.SessionService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class OrderRepository {
    @Autowired
    private SessionService service;

    public List<Order> findAll() {
        try (Session session = service.getFactory().getCurrentSession()) {
            session.beginTransaction();
            List<Order> orders = session.createQuery("from Order").getResultList();
            session.getTransaction().commit();
            return Collections.unmodifiableList(orders);
        }
    }

    public Order findById(Long id) {
        try (Session session = service.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Order order = session.find(Order.class, id);
            return order;
        }
    }

    public void saveOrUpdate(Order customer) {
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
            Order order = session.get(Order.class, id);
            session.delete(order);
            session.getTransaction().commit();
        }
    }

}
