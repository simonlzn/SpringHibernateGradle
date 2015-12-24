package org.sphic.Service.DAO;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.sphic.HibernateConfig.HibernateUtil;
import org.sphic.Model.Contour;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class ContourDao extends Dao {
    public int save(Contour contour){
        Session session = HibernateUtil.currentSession();
        int id = (Integer) session.save(contour);
        session.flush();
        return id;
    }

    public List<Contour> getByStructureId(int id){
        Session session = HibernateUtil.currentSession();

        Criteria criteria = session.createCriteria(Contour.class).add(Restrictions.eq("structure.id", id));

        List list = criteria.list();
        return list;
    }

    public List<Contour> getBySliceId(int id){
        Session session = HibernateUtil.currentSession();
        Criteria criteria = session.createCriteria(Contour.class).add(Restrictions.eq("slice.id", id));

        List list = criteria.list();
        return list;
    }
}
