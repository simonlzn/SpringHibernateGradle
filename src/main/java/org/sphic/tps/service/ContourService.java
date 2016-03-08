package org.sphic.tps.service;

import org.sphic.tps.model.Contour;
import org.sphic.tps.service.DAO.ContourDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContourService {
    private ContourDAO contourDao;

    @Autowired
    public ContourService(ContourDAO contourDao) {

        this.contourDao = contourDao;
    }

    public String ConstructDataByStructureId(int id){
        List<Contour> contours = contourDao.getByStructureId(id);

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        for (Contour contour : contours) {
            stringBuffer.append("(");
            stringBuffer.append(contour.getContourData());
            stringBuffer.append(")");
            stringBuffer.append(",");
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
