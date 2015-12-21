package org.sphic.Service;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Sequence;
import org.dcm4che3.data.Tag;
import org.dcm4che3.io.DicomInputStream;
import org.sphic.Model.*;
import org.sphic.Service.DAO.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class StructureExtractService {

    private int seriesId;
    private Dao dao;

    @Autowired
    public StructureExtractService(Dao dao) {
        this.dao = dao;
    }

    public void writeStructure(MultipartFile file, Map<String, Slice> sliceMap, Map<String, Series> seriesMap) throws Exception {

            InputStream is = file.getInputStream();
            DicomInputStream dis = new DicomInputStream(is);

            Attributes dcmObj = dis.readDataset(-1, -1);

            List<StructureSet> structureSet = new ArrayList<StructureSet>();
            StructureSet nStructureSet = new StructureSet(
                    dcmObj.getString(Tag.StructureSetName), new Date(), null, null, dcmObj.getString(Tag.StructureSetDescription), null);
            List<Structure> structures = new ArrayList<Structure>();
            Sequence structureSequence = dcmObj.getSequence(Tag.StructureSetROISequence);
            for (int i = 0; i < structureSequence.size(); ++i) {
                Structure iStructure = new Structure(Integer.parseInt(structureSequence.get(i).getString(Tag.ROINumber)), structureSequence.get(i).getString(Tag.ROIName),
                        new Date(), null, null, null, null);
                List<Contour> iContours = new ArrayList<Contour>();
                Sequence ROIContourSequence = dcmObj.getSequence(Tag.ROIContourSequence);
                for (int j = 0; j < ROIContourSequence.size(); ++j) {
                    if (Integer.parseInt(ROIContourSequence.get(j).getString(Tag.ReferencedROINumber)) == (iStructure.getROINumber())) {
                        iStructure.setROIcolor(String.join(",", ROIContourSequence.get(j).getStrings(Tag.ROIDisplayColor)));
                        Sequence ContourSequence = ROIContourSequence.get(j).getSequence(Tag.ContourSequence);
                        for (int k = 0; k < ContourSequence.size(); ++k) {
                            Sequence ContourImageSequence = ContourSequence.get(k).getSequence(Tag.ContourImageSequence);
                            Contour kContour = new Contour(ContourImageSequence.get(0).getString(Tag.ReferencedSOPInstanceUID), new Date(), null, null, null, String.join(",", ContourSequence.get(k).getStrings(Tag.ContourData)));
                            if (sliceMap.containsKey(ContourImageSequence.get(0).getString(Tag.ReferencedSOPInstanceUID)))
                                kContour.setSlice(sliceMap.get(ContourImageSequence.get(0).getString(Tag.ReferencedSOPInstanceUID)));
                            iContours.add(kContour);
                            kContour.setStructure(iStructure);
                        }
                        iStructure.setContours(iContours);
                        break;
                    }
                }
                structures.add(iStructure);
                iStructure.setStructureSet(nStructureSet);
            }

            nStructureSet.setStructures(structures);
            dao.save(nStructureSet);
            structureSet.add(nStructureSet);
            if (seriesMap.containsKey(dcmObj.getString(Tag.SeriesInstanceUID))) {
                Series series = seriesMap.get(dcmObj.getString(Tag.SeriesInstanceUID));
                series.setStructureSets(structureSet);
                nStructureSet.setSeries(series);
                seriesId = series.getSeriesId();
            }
            is.close();
    }

    public int getSeriesID() {
        return seriesId;
    }
}
