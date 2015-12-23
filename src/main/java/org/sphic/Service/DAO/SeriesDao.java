package org.sphic.Service.DAO;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.sphic.HibernateConfig.HibernateUtil;
import org.sphic.Model.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class SeriesDao extends Dao{

    public Series getBySeriesUID(String uid){
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(Series.class).add(Restrictions.eq("seriesInsUID", uid));

        List seriesList = criteria.list();
        tx.commit();

        if (seriesList.isEmpty())
            return null;

        return (Series) seriesList.get(0);
    }
}
