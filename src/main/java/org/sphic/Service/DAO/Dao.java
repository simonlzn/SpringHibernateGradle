package org.sphic.Service.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Transactional
@Repository
public class Dao {

    private SessionFactory sessionFactory;

    @Autowired
    public Dao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public <T> Serializable save(T o)
    {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        Serializable id = session.save(o);
        tx.commit();
        return  id;
    }

    public <T> void update(T o)
    {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        session.update(o);
        tx.commit();
    }

    public <T> void saveOrUpdate(T o)
    {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        session.saveOrUpdate(o);
        tx.commit();
    }

    public <T> T get(Class<T>c, Serializable id)
    {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        T o = (T) session.get(c, id);
        tx.commit();
        return o;
    }
}
