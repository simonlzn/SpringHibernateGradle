package org.sphic.Service.DAO;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.sphic.Model.Slice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class SliceDao extends Dao{
    private SessionFactory sessionFactory;

    @Autowired
    public SliceDao(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public List<Slice> getBySeriesId(int id){
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        Criteria criteria = session.createCriteria(Slice.class).add(Restrictions.eq("series.id", id));
        List sliceList = criteria.list();

        tx.commit();
        return sliceList;
    }

    public Slice getByViewAndNumber(int seriesId, char view, int number){
        Session session = sessionFactory.getCurrentSession();

        Transaction tx = session.beginTransaction();
        Criteria cr = session.createCriteria(Slice.class).add(Restrictions.eq("series.id", seriesId)).add(Restrictions.eq("view", view)).add(Restrictions.eq("number", number));
        List<Slice> sliceList = cr.list();

        tx.commit();
        return sliceList.get(0);
    }
}
