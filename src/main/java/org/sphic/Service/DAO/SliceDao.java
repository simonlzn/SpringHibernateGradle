package org.sphic.Service.DAO;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.sphic.HibernateConfig.HibernateUtil;
import org.sphic.Model.Slice;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class SliceDao extends Dao{

    public List<Slice> getBySeriesId(int id){
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        Criteria criteria = session.createCriteria(Slice.class).add(Restrictions.eq("series.id", id));
        List sliceList = criteria.list();

        tx.commit();
        return sliceList;
    }

    public Slice getByViewAndNumber(int seriesId, char view, int number){
        Session session = HibernateUtil.currentSession();

        Transaction tx = session.beginTransaction();
        Criteria cr = session.createCriteria(Slice.class).add(Restrictions.eq("series.id", seriesId)).add(Restrictions.eq("view", view)).add(Restrictions.eq("number", number));
        List<Slice> sliceList = cr.list();

        tx.commit();

        if (sliceList.isEmpty())
            return null;

        return sliceList.get(0);
    }
}
