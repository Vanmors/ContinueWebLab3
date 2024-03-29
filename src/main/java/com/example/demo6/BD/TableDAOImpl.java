package com.example.demo6.BD;

import com.example.demo6.ResultTable;
import com.example.demo6.XBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TableDAOImpl implements TableDAO{
//    private EntityManagerFactory managerFactory;
//    @PersistenceContext
//    private EntityManager manager;
//    private EntityTransaction transaction;

//    private final SessionFactory sessionFactory = new Configuration()
//            .configure("hibernate.cfg.xml")
//            .addAnnotatedClass(ResultTable.class)
//            .buildSessionFactory();

//public TableDAOImpl() {
//    managerFactory = Persistence.createEntityManagerFactory("default");
//    manager = managerFactory.createEntityManager();
//    transaction = manager.getTransaction();
//}

    @Override
    public ResultTable findById(int id) {
        return ConnectToDB.getSessionFactory().openSession().get(ResultTable.class, id);
    }

    @Override
    public void save(ResultTable resultTable) {
        Session session = ConnectToDB.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(resultTable);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(ResultTable resultTable) {

    }

    @Override
    public List<ResultTable> findAll() {
//        return ConnectToDB.getSessionFactory().openSession().createQuery("SELECT a FROM dots a", ResultTable.class).getResultList();
        CriteriaBuilder cb = ConnectToDB.getSessionFactory().openSession().getCriteriaBuilder();
        CriteriaQuery<ResultTable> cq = cb.createQuery(ResultTable.class);
        Root<ResultTable> rootEntry = cq.from(ResultTable.class);
        CriteriaQuery<ResultTable> all = cq.select(rootEntry);

        TypedQuery<ResultTable> allQuery = ConnectToDB.getSessionFactory().openSession().createQuery(all);
        return allQuery.getResultList();
    }


    
//    public int countOfPoints(){
//        return ConnectToDB.getSessionFactory().openSession().createQuery("select count all from dots");
//    }

//    @Override
//    public int findAll() {
//        Session session = ConnectToDB.getSessionFactory().openSession();
//        Transaction tx1 = session.beginTransaction();
//        return session.createQuery("SELECT hit FROM dots hit").;
////        tx1.commit();
////        session.close();
//    }


//    @Override
//    public void save(ResultTable resultTable) {
//        Session session = ConnectToDB.getSessionFactory().openSession();
//        Transaction tx1 = session.beginTransaction();
//        session.save(resultTable);
//        tx1.commit();
//        session.close();
//    }
//    @Override
//    public void delete(ResultTable resultTable) {
//        Session session = ConnectToDB.getSessionFactory().openSession();
//        Transaction tx1 = session.beginTransaction();
//        session.delete(resultTable);
//        tx1.commit();
//        session.close();
//    }
//    @Override
//    public List<ResultTable> findAll() {
//        List<ResultTable> results = ConnectToDB.getSessionFactory().openSession().createQuery("From ResultTable ").list();
//        return results;
//
//    }
}