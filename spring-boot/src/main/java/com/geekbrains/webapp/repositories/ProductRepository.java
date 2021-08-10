package com.geekbrains.webapp.repositories;

import com.geekbrains.webapp.model.Product;
import com.geekbrains.webapp.services.SessionService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    @Autowired
    private SessionService service;

    public List<Product> findAll() {
        try (Session session = service.getFactory().getCurrentSession()) {
            session.beginTransaction();
            List<Product> products = session.createQuery("from Product").getResultList();
            session.getTransaction().commit();
            return Collections.unmodifiableList(products);
        }
    }

    public Product findById(Long id) {
        try (Session session = service.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Product product = session.find(Product.class, id);
            return product;
        }
    }

    public void saveOrUpdate(Product product) {
        try (Session session = service.getFactory().getCurrentSession()) {
            session.beginTransaction();
            session.merge(product);
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
