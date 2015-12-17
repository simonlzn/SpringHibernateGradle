package org.sphic.Service.DAO;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.sphic.HibernateConfig.HibernateUtil;
import org.sphic.Model.Contour;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class ContourDao {
    public List<Contour> getByStructureId(int id){
        Session session = HibernateUtil.currentSession();

        Criteria criteria = session.createCriteria(Contour.class).add(Restrictions.eq("structure.id", id));

        return criteria.list();
    }
}
