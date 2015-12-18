package org.sphic.Service;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.io.DicomInputStream;
import org.sphic.Model.*;
import org.sphic.Service.DAO.Dao;
import org.sphic.Service.DAO.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Component
public class ImageExtractService {

    private int seriesId;
    private Dao dao;
    private PatientDao patientDao;
    private boolean HasBeenSavedToDatabase = false;
    private Series nSeries;
    private String patientId = null;

    @Autowired
    public ImageExtractService(Dao dao, PatientDao patientDao) {
        this.dao = dao;
        this.patientDao = patientDao;
    }

    public void writeImage(MultipartFile file, Map<String, Slice> sliceMap, Map<String, Series> seriesMap) throws Exception {

                InputStream is = file.getInputStream();
                DicomInputStream dis = new DicomInputStream(is);

                Attributes dcmObj = dis.readDataset(-1, -1);

                if (!HasBeenSavedToDatabase) {
                    HasBeenSavedToDatabase = true;

                    Patient p = new Patient(Integer.getInteger(
                            dcmObj.getString(Tag.PatientID), 0),
                            dcmObj.getString(Tag.PatientName), "",
                            dcmObj.getString(Tag.PatientAddress), "",
                            Integer.getInteger(
                                    dcmObj.getString(Tag.PatientAge), 0),
                            null, null);

                    List<Study> studies = new ArrayList<Study>();
                    Study nStudy = new Study(dcmObj
                            .getString(Tag.StudyID),
                            dcmObj.getString(Tag.StudyInstanceUID),
                            dcmObj.getDate(Tag.StudyDateAndTime),
                            dcmObj.getDate(Tag.StudyDateAndTime), null, null,
                            dcmObj.getString(Tag.StudyDescription), dcmObj.getString(Tag.ReferringPhysicianName),
                            dcmObj.getString(Tag.AccessionNumber), dcmObj.getString(Tag.InstitutionName), null);

                    List<Series> series = new ArrayList<Series>();
                    nSeries = new Series(Integer.parseInt(dcmObj
                            .getString(Tag.SeriesNumber)),
                            dcmObj.getString(Tag.SeriesInstanceUID),
                            dcmObj.getInt(Tag.SeriesNumber, 0),
                            new Date(),
                            dcmObj.getString(Tag.SeriesDescription),
                            dcmObj.getString(Tag.Modality),
                            dcmObj.getString(Tag.Manufacturer),
                            dcmObj.getString(Tag.ManufacturerModelName),
                            dcmObj.getDate(Tag.StudyDateAndTime),
                            dcmObj.getDate(Tag.StudyDateAndTime), null, null);

                    seriesMap.put(dcmObj.getString(Tag.SeriesInstanceUID),nSeries);
                    ImageSeries imageSeries = new ImageSeries(
                            dcmObj.getString(Tag.SeriesInstanceUID),
                            dcmObj.getDouble(Tag.SliceThickness, 0.0),
                            String.join(",", dcmObj.getStrings(Tag.ImageOrientationPatient)),
                            dcmObj.getInt(Tag.Rows, 512),
                            dcmObj.getInt(Tag.Columns, 512),
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

                    List<Slice> Slices= new ArrayList<Slice>();
                    Slice nSlice = new Slice('T',0,dcmObj.getInt(Tag.Rows,0),dcmObj.getInt(Tag.Columns,0),Double.parseDouble(dcmObj.getStrings(Tag.PixelSpacing)[0]),Double.parseDouble(dcmObj.getStrings(Tag.PixelSpacing)[1]),dcmObj.getString(Tag.SOPInstanceUID),
                            Integer.parseInt(dcmObj.getString(Tag.InstanceNumber)),
                            dcmObj.getDouble(Tag.SliceLocation, 0.0),
                            String.join(",", dcmObj.getStrings(Tag.ImagePositionPatient)),
                            new Date(), null, null, null, null, null);

                    sliceMap.put(dcmObj.getString(Tag.SOPInstanceUID), nSlice);
                    Slices.add(nSlice);
                    nSlice.setSeries(nSeries);
                    nSeries.setSlices(Slices);

                    imageSeries.setSeries(nSeries);
                    nSeries.setImageSeries(imageSeries);
                    nSeries.setStudy(nStudy);

                    series.add(nSeries);
                    nStudy.setSeries(series);
                    nStudy.setPatient(p);
                    studies.add(nStudy);
                    p.setStudies(studies);
                    patientId = patientDao.save(p);

                    is.close();
                } else {

                    Slice nSlice = new Slice('T',0,dcmObj.getInt(Tag.Rows,0),dcmObj.getInt(Tag.Columns,0),Double.parseDouble(dcmObj.getStrings(Tag.PixelSpacing)[0]),Double.parseDouble(dcmObj.getStrings(Tag.PixelSpacing)[1]),dcmObj.getString(Tag.SOPInstanceUID),
                            Integer.parseInt(dcmObj.getString(Tag.InstanceNumber)),
                            dcmObj.getDouble(Tag.SliceLocation, 0.0),
                            String.join(",", dcmObj.getStrings(Tag.ImagePositionPatient)),
                            new Date(), null, null, null, null, null);

                    sliceMap.put(dcmObj.getString(Tag.SOPInstanceUID), nSlice);
                    nSlice.setSeries(nSeries);
                    dao.saveOrUpdate(nSlice);

                    is.close();
                }

                byte[] bytes = file.getBytes();
                if (patientId == null)
                    throw new Exception("patient ID can not be retrieved");

                String name = "~/data/" + patientId.toString() + "/" + file.getOriginalFilename();

                File dataFolder = new File("~/data/" + patientId.toString() + "/" );
                if (dataFolder.exists() || dataFolder.mkdirs()) {
                    BufferedOutputStream stream =
                            new BufferedOutputStream(new FileOutputStream(new File(name)));
                    stream.write(bytes);
                    stream.close();

                    System.out.println(name + " is uploaded!");
                }
    }
}