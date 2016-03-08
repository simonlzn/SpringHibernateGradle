package org.sphic.tps.service;

import org.sphic.tps.model.ImageSeries;
import org.sphic.tps.service.DAO.ImageSeriesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ImageSeriesService {
    private ImageSeriesDAO imageSeriesDao;

    @Autowired
    public ImageSeriesService(ImageSeriesDAO imageSeriesDao) {
        this.imageSeriesDao = imageSeriesDao;
    }

    public String GetVolume(int id){
        ImageSeries imageSeries = imageSeriesDao.getBySeriesId(id);
        return "[" + imageSeries.getRows()  + "," + imageSeries.getColumns() + "," + imageSeries.getSliceNum() + "]";
    }
}
