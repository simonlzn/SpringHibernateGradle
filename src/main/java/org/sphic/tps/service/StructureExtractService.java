package org.sphic.tps.service;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Sequence;
import org.dcm4che3.data.Tag;
import org.dcm4che3.io.DicomInputStream;
import org.sphic.tps.model.*;
import org.sphic.tps.service.DAO.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;

@Component
@Transactional
public class StructureExtractService {

    private Dao dao;

    @Autowired
    public StructureExtractService(Dao dao) {
        this.dao = dao;
    }

    public void writeStructure(MultipartFile file, Map<String, Slice> sliceMap, Map<String, Series> seriesMap) throws Exception {

        InputStream is = file.getInputStream();
        DicomInputStream dis = new DicomInputStream(is);

        Attributes dcmObj = dis.readDataset(-1, -1);

        Set<StructureSet> structureSet = new HashSet<StructureSet>();
        StructureSet nStructureSet = new StructureSet(
                dcmObj.getString(Tag.StructureSetName), new Date(), null, null, dcmObj.getString(Tag.StructureSetDescription), null);
        Set<Structure> structures = new HashSet<Structure>();
        Sequence structureSequence = dcmObj.getSequence(Tag.StructureSetROISequence);
        for (int i = 0; i < structureSequence.size(); ++i) {
            Structure iStructure = new Structure(Integer.parseInt(structureSequence.get(i).getString(Tag.ROINumber)), structureSequence.get(i).getString(Tag.ROIName),
                    new Date(), null, null, null, null);
            Set<Contour> iContours = new HashSet<Contour>();
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

        structureSet.add(nStructureSet);
        String strReferencedSeriesUID = dcmObj.getSequence(Tag.ReferencedFrameOfReferenceSequence).get(0).getSequence(Tag.RTReferencedStudySequence).get(0).getSequence(Tag.RTReferencedSeriesSequence).get(0).getString(Tag.SeriesInstanceUID);
        if (seriesMap.containsKey(strReferencedSeriesUID)) {
            Series series = seriesMap.get(strReferencedSeriesUID);
            series.setStructureSets(structureSet);
            nStructureSet.setSeries(series);
        }
        dao.save(nStructureSet);
        is.close();
    }

}
