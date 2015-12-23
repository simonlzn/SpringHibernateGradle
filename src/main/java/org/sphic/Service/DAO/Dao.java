package org.sphic.Service.DAO;

import org.hibernate.Session;
import org.sphic.HibernateConfig.HibernateUtil;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Transactional
@Repository
public class Dao {

    public <T> Serializable save(T o)
    {
        Session session = HibernateUtil.currentSession();

        Serializable id = session.save(o);
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

        session.saveOrUpdate(o);
    }

    public <T> T get(Class<T>c, Serializable id)
    {
        Session session = HibernateUtil.currentSession();

        T o = (T) session.get(c, id);
        return o;
    }
}
