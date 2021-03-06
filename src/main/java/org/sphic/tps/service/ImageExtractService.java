package org.sphic.tps.service;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.io.DicomInputStream;
import org.sphic.tps.model.*;
import org.sphic.tps.service.DAO.Dao;
import org.sphic.tps.service.DAO.PatientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Component
@Transactional
public class ImageExtractService {

    private Dao dao;
    private PatientDAO patientDao;
    private Series nSeries;

    @Autowired
    public ImageExtractService(Dao dao, PatientDAO patientDao) {
        this.dao = dao;
        this.patientDao = patientDao;
    }

    public int writePatient(MultipartFile file, Map<String, Series> seriesMap, int sliceNum) throws Exception {

        InputStream is = file.getInputStream();
        DicomInputStream dis = new DicomInputStream(is);

        Attributes dcmObj = dis.readDataset(-1, -1);

        Patient p = new Patient(Integer.getInteger(
                dcmObj.getString(Tag.PatientID), 0),
                dcmObj.getString(Tag.PatientName), "",
                dcmObj.getString(Tag.PatientAddress), "",
                Integer.getInteger(
                        dcmObj.getString(Tag.PatientAge), 0),
                null, null);

        Set<Study> studies = new HashSet<Study>();
        Study nStudy = new Study(dcmObj.getString(Tag.StudyID),
                dcmObj.getString(Tag.StudyInstanceUID),
                dcmObj.getDate(Tag.StudyDateAndTime),
                dcmObj.getDate(Tag.StudyDateAndTime), null, null,
                dcmObj.getString(Tag.StudyDescription),
                dcmObj.getString(Tag.AccessionNumber), dcmObj.getString(Tag.InstitutionName), null);

        Set<Series> series = new HashSet<Series>();
        nSeries = new Series(dcmObj
                .getString(Tag.SeriesNumber),
                dcmObj.getString(Tag.SeriesInstanceUID),
                dcmObj.getInt(Tag.SeriesNumber, 0),
                new Date(),
                dcmObj.getString(Tag.SeriesDescription),
                dcmObj.getString(Tag.Modality),
                dcmObj.getString(Tag.Manufacturer),
                dcmObj.getString(Tag.ManufacturerModelName),
                dcmObj.getDate(Tag.StudyDateAndTime),
                dcmObj.getDate(Tag.StudyDateAndTime), null, null);

        seriesMap.put(dcmObj.getString(Tag.SeriesInstanceUID), nSeries);
        ImageSeries imageSeries = new ImageSeries(
                dcmObj.getString(Tag.SeriesInstanceUID),
                dcmObj.getDouble(Tag.SliceThickness, 0.0),
                String.join(",", dcmObj.getStrings(Tag.ImageOrientationPatient)),
                dcmObj.getInt(Tag.Rows, 512),
                dcmObj.getInt(Tag.Columns, 512),
                sliceNum,
                dcmObj.getString(Tag.PatientPosition),
                String.join(",", dcmObj.getStrings(Tag.PixelSpacing)),
                dcmObj.getDouble(Tag.RescaleSlope, 1.0),
                dcmObj.getDouble(Tag.RescaleIntercept, 0.0),
                String.join(",", dcmObj.getStrings(Tag.ImageType)),
                dcmObj.getString(Tag.DerivationDescription),
                dcmObj.getString(Tag.PatientOrientation),
                dcmObj.getString(Tag.SpecificCharacterSet),
                dcmObj.getString(Tag.SamplesPerPixel),
                dcmObj.getString(Tag.PhotometricInterpretation),
                dcmObj.getInt(Tag.BitsAllocated, 0),
                dcmObj.getInt(Tag.BitsStored, 0),
                dcmObj.getInt(Tag.HighBit, 0),
                dcmObj.getInt(Tag.PixelRepresentation, 0),
                dcmObj.getInt(Tag.SmallestImagePixelValue, 0),
                dcmObj.getInt(Tag.LargestImagePixelValue, 0));


        imageSeries.setSeries(nSeries);
        nSeries.setImageSerieses(imageSeries);
        nSeries.setStudy(nStudy);

        series.add(nSeries);
        nStudy.setSerieses(series);
        nStudy.setPatient(p);
        studies.add(nStudy);
        p.setStudies(studies);
        patientDao.save(p);

        is.close();
        return nSeries.getSeriesId();
    }

    public void writeImage(MultipartFile file, Map<String, Slice> sliceMap) throws Exception {

        InputStream is = file.getInputStream();
        DicomInputStream dis = new DicomInputStream(is);

        Attributes dcmObj = dis.readDataset(-1, -1);

        Slice nSlice = new Slice('T', 0, dcmObj.getInt(Tag.Rows, 0), dcmObj.getInt(Tag.Columns, 0), Double.parseDouble(dcmObj.getStrings(Tag.PixelSpacing)[0]), Double.parseDouble(dcmObj.getStrings(Tag.PixelSpacing)[1]), dcmObj.getString(Tag.SOPInstanceUID),
                Integer.parseInt(dcmObj.getString(Tag.InstanceNumber)),
                dcmObj.getDouble(Tag.SliceLocation, 0.0),
                String.join(",", dcmObj.getStrings(Tag.ImagePositionPatient)),
                new Date(), null, null, null, null);

        sliceMap.put(dcmObj.getString(Tag.SOPInstanceUID), nSlice);
        nSlice.setSeries(nSeries);
        dao.saveOrUpdate(nSlice);

        is.close();

        byte[] bytes = file.getBytes();
        if (nSeries.getSeriesId() == 0)
            throw new Exception("Series ID can not be retrieved");

        String name = "~/data/" + Integer.toString(nSeries.getSeriesId()) + "/" + file.getOriginalFilename();

        File dataFolder = new File("~/data/" + Integer.toString(nSeries.getSeriesId()) + "/");
        if (dataFolder.exists() || dataFolder.mkdirs()) {
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(name)));
            stream.write(bytes);
            stream.close();

            System.out.println(name + " is uploaded!");
        }
    }

}