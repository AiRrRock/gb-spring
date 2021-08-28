package com.geekbrains.webapp.services;

import com.geekbrains.webapp.model.Order;
import com.geekbrains.webapp.model.Product;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.Collections;
import java.util.List;

@Service
public class ProductDAO {
    private SessionService service;

    @Autowired
    public ProductDAO(SessionService service) {
        this.service = service;
    }


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
            product.setOrders(Collections.emptyList());
            session.getTransaction().commit();
            return product;
        }
    }

    public Product findByIdWithOrders(Long id) {
        try (Session session = service.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Product product = session
                    .createNamedQuery("productWithOrders", Product.class)
                    .setParameter("id", id)
                    .getSingleResult();
            for(Order o : product.getOrders()){
                o.getCustomer();
            }
            session.getTransaction().commit();
            return product;
        } catch (NoResultException e) {
            return findById(id);
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
