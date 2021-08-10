package com.geekbrains.webapp.services;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SessionService {
    private SessionFactory factory;

    @PostConstruct
    public void init() {
        factory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public SessionFactory getFactory() {
        return factory;
    }

    public void shutdown() {
        factory.close();
    }
}
