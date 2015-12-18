package org.sphic.Service.DAO;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.sphic.HibernateConfig.HibernateUtil;
import org.sphic.Model.Series;
import org.sphic.Model.Slice;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class SeriesDao extends Dao{
    public Series getBySeriesUID(String uid){
        Session session = HibernateUtil.currentSession();

        Criteria criteria = session.createCriteria(Series.class).add(Restrictions.eq("seriesInsUID", uid));

        return (Series)criteria.list().get(0);
    }
}
