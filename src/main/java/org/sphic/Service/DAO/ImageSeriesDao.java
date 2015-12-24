package org.sphic.Service.DAO;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.sphic.HibernateConfig.HibernateUtil;
import org.sphic.Model.ImageSeries;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional(readOnly = true)
public class ImageSeriesDao extends Dao{
    public ImageSeries getBySeriesId(int id){
        Session session = HibernateUtil.currentSession();
        Criteria criteria = session.createCriteria(ImageSeries.class).add(Restrictions.eq("series.id", id));

        List imageSeriesList = criteria.list();

        if (imageSeriesList.isEmpty())
            return null;

        return (ImageSeries) imageSeriesList.get(0);
    }
}
