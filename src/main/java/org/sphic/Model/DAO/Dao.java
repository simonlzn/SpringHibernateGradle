package org.sphic.Model.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sphic.HibernateConfig.HibernateUtil;
import org.sphic.Model.Series;
import org.sphic.Model.Slice;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Transactional
@Repository
public class Dao {

    public <T> void save(T o)
    {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        session.save(o);
        tx.commit();
    }

    public <T> T get(Class<T>c, Serializable id)
    {
        Session session = HibernateUtil.currentSession();

        return (T)session.get(c, id);
    }
}
