package org.sphic.Service.DAO;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.sphic.HibernateConfig.HibernateUtil;
import org.sphic.Model.Patient;
import org.sphic.Model.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
@Repository
public class Dao {

    public <T> Serializable save(T o)
    {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        Serializable id = session.save(o);
        tx.commit();
        return  id;
    }

    public <T> void update(T o)
    {

        Session session = HibernateUtil.currentSession();
        session.update(o);

    }

    public <T> void saveOrUpdate(T o)
    {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        session.saveOrUpdate(o);
        tx.commit();
    }

    public <T> T get(Class<T>c, Serializable id)
    {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        T o = (T) session.get(c, id);
        tx.commit();
        return o;
    }
}
