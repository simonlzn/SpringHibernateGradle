package org.sphic.Service.DAO;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.sphic.Model.Contour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class ContourDao extends Dao {

    private SessionFactory sessionFactory;

    @Autowired
    public ContourDao(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public List<Contour> getByStructureId(int id){
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        Criteria criteria = session.createCriteria(Contour.class).add(Restrictions.eq("structure.id", id));

        List list = criteria.list();
        tx.commit();
        return list;
    }

    public List<Contour> getBySliceId(int id){
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(Contour.class).add(Restrictions.eq("slice.id", id));

        List list = criteria.list();
        tx.commit();
        return list;
    }

    public int save(Contour contour){
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        int id = (Integer) session.save(contour);
        session.flush();
        tx.commit();
        return id;
    }
}
