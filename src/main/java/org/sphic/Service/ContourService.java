package org.sphic.Service;

import org.sphic.Model.Contour;
import org.sphic.Service.DAO.ContourDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContourService {
    private ContourDao contourDao;

    @Autowired
    public ContourService(ContourDao contourDao) {

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
