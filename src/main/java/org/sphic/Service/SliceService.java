package org.sphic.Service;

import org.sphic.Model.DAO.SliceDao;
import org.sphic.Model.Slice;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;

public class SliceService {
    private SliceDao sliceDao;

    @Autowired
    public SliceService(SliceDao sliceDao) {
        this.sliceDao = sliceDao;
    }

    public void SortAndUpdateSlices(int id){
        List<Slice> slices = sliceDao.getByStructureId(id);
        slices.sort(new Comparator<Slice>() {
            @Override
            public int compare(Slice o1, Slice o2) {
                return (int)(o1.getSlice_location() - o2.getSlice_location());
            }
        });

        int count = 1;
        for (Slice slice:slices) {
            slice.setNumber(count);
            sliceDao.save(slice);
            count++;
        }
    }
}
