package org.sphic.tps.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sphic.tps.hibernateConfig.HibernateUtil;
import org.sphic.tps.model.Series;
import org.sphic.tps.service.DAO.SeriesDAO;
import org.sphic.tps.service.DAO.SliceDAO;
import org.sphic.tps.model.Slice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@Transactional
public class SliceService {
    private SliceDAO sliceDao;
    private SeriesDAO seriesDAO;

    @Autowired
    public SliceService(SliceDAO sliceDao, SeriesDAO seriesDAO) {
        this.sliceDao = sliceDao;
        this.seriesDAO = seriesDAO;
    }

    public void SortAndUpdateSlices(int id){
        List<Slice> slices = sliceDao.getBySeriesId(id);
        slices.sort(new Comparator<Slice>() {
            @Override
            public int compare(Slice o1, Slice o2) {
                return (int)(o1.getSliceLocation() - o2.getSliceLocation());
            }
        });

        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        int count = 1;
        for (Slice slice:slices) {
            slice.setNumber(count);
            sliceDao.save(slice);
            count++;
        }

        tx.commit();
    }

    public void saveOrUpdateData(int seriesId, char view, int number, Map userData){
        Slice slice = sliceDao.getByViewAndNumber(seriesId, view,number);
        if (slice == null)
            slice = new Slice(view, number, Integer.parseInt(userData.get("row").toString()), Integer.parseInt(userData.get("column").toString()), Double.parseDouble(userData.get("rowspacing").toString()), Double.parseDouble(userData.get("columnspacing").toString()), null, 0, 0, null, new Date(), new Date(),  null, userData.get("data").toString(), null);
        else
            slice.setData(userData.get("data").toString());

        Series series = seriesDAO.findById(seriesId);
        slice.setSeries(series);
        sliceDao.save(slice);
    }
}
