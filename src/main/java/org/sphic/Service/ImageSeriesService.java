package org.sphic.Service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sphic.HibernateConfig.HibernateUtil;
import org.sphic.Model.ImageSeries;
import org.sphic.Model.Slice;
import org.sphic.Service.DAO.ImageSeriesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Component
@Transactional
public class ImageSeriesService {
    private ImageSeriesDao imageSeriesDao;

    @Autowired
    public ImageSeriesService(ImageSeriesDao imageSeriesDao) {
        this.imageSeriesDao = imageSeriesDao;
    }

    public String GetVolume(int id){
        ImageSeries imageSeries = imageSeriesDao.getBySeriesId(id);
        return "[" + imageSeries.getRows()  + "," + imageSeries.getColumns() + "," + imageSeries.getSliceNum() + "]";
    }
}
