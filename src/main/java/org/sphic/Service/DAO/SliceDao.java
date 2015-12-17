package org.sphic.Service.DAO;

import org.hibernate.Criteria;
import org.hibernate.Session;
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

        Criteria criteria = session.createCriteria(Slice.class).add(Restrictions.eq("series.id", id));

        return criteria.list();
    }
}
