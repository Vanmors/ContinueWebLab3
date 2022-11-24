package com.example.demo6.BD;

import com.example.demo6.ResultTable;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class ConnectToDB {
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    private ConnectToDB(){}

    public static SessionFactory getSessionFactory() {
        Configuration configuration = null;
        if (sessionFactory == null) {
            try {
                configuration = new Configuration().configure();
                configuration.addAnnotatedClass(ResultTable.class);
                serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();


            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
//        return sessionFactory;
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
