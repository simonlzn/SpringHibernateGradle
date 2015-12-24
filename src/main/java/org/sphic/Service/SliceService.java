package org.sphic.Service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sphic.HibernateConfig.HibernateUtil;
import org.sphic.Model.Series;
import org.sphic.Service.DAO.SliceDao;
import org.sphic.Model.Slice;
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
    private SliceDao sliceDao;

    @Autowired
    public SliceService(SliceDao sliceDao) {
        this.sliceDao = sliceDao;
    }

    public void SortAndUpdateSlices(int id){
        List<Slice> slices = sliceDao.getBySeriesId(id);
        slices.sort(new Comparator<Slice>() {
            @Override
            public int compare(Slice o1, Slice o2) {
                return (int)(o1.getSlice_location() - o2.getSlice_location());
            }
        });

        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        int count = 1;
        for (Slice slice:slices) {
            slice.setNumber(count);
            sliceDao.update(slice);
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

        Series series = sliceDao.get(Series.class, seriesId);
        slice.setSeries(series);
        sliceDao.save(slice);
    }
}
