package org.sphic.Controller;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Sequence;
import org.dcm4che3.data.Tag;
import org.dcm4che3.io.DicomInputStream;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sphic.HibernateConfig.HibernateUtil;
import org.sphic.Message.MessageQueue;
import org.sphic.Model.Images;
import org.sphic.Model.ImageSeries;
import org.sphic.Model.Patient;
import org.sphic.Model.Series;
import org.sphic.Model.Study;
import org.sphic.Model.StructureSet;
import org.sphic.Model.Structure;
import org.sphic.Model.Contour;
import org.sphic.Model.Slice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

import java.awt.Image;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Component
@RequestMapping("/file")
public class FileController {
    private final MessageQueue messageQueue;

    @Autowired
    public FileController(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public @ResponseBody String download( final HttpServletResponse response) throws InterruptedException {
//        response.setHeader("Cache-Control", "private, max-age=86400");
//        response.setHeader("Expires", new Date(180,1,1).toGMTString());
//        response.setHeader("Last-Modified", new Date(80,1,1).toGMTString());
        Thread.sleep(5000);
        return "very weird string";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public
	@ResponseBody
	String Upload(@RequestParam("file") MultipartFile[] files) {
		Boolean HasBeenSavedToDatabase = false;
		String patientId = null;
		int SeriesID = 0;
		Series nSeries = null;
		Map<String, Slice> sliceMap = new HashMap<String, Slice>();
		Map<String, Series> seriesMap = new HashMap<String, Series>();
		for (MultipartFile file : files) {

			if (!file.isEmpty()) {
				try {

					InputStream is = file.getInputStream();
					DicomInputStream dis = new DicomInputStream(is);

					Attributes dcmObj = dis.readDataset(-1, -1);
					if (dcmObj.getString(Tag.Modality).equals("CT")) {

						if (!HasBeenSavedToDatabase) {
							HasBeenSavedToDatabase = true;
							Session session = HibernateUtil.currentSession();
							Transaction tx1 = session.beginTransaction();
							Patient p = new Patient(Integer.getInteger(
									dcmObj.getString(Tag.PatientID), 0),
									dcmObj.getString(Tag.PatientName), "",
									dcmObj.getString(Tag.PatientAddress), "",
									Integer.getInteger(
											dcmObj.getString(Tag.PatientAge), 0),
									null, null);


							List<Study> studies = new ArrayList<Study>();
							Study nStudy = new Study(Integer.parseInt(dcmObj
									.getString(Tag.StudyID)),
									dcmObj.getString(Tag.StudyInstanceUID),
									dcmObj.getDate(Tag.StudyDateAndTime),
									dcmObj.getDate(Tag.StudyDateAndTime), null, null,
									dcmObj.getString(Tag.StudyDescription), dcmObj.getString(Tag.ReferringPhysicianName),
									dcmObj.getString(Tag.AccessionNumber), dcmObj.getString(Tag.InstitutionName), null);

							List<Series> series = new ArrayList<Series>();
							nSeries = new Series(Integer.parseInt(dcmObj
									.getString(Tag.SeriesNumber)),
									nStudy.getStudyId(),
									dcmObj.getString(Tag.SeriesInstanceUID),
									dcmObj.getInt(Tag.SeriesNumber, 0),
								/*new SimpleDateFormat("yyyyMMddhhmmss").parse(dcmObj.getString(Tag.SeriesDate)+
								dcmObj.getString(Tag.SeriesTime))*/ new Date(),
<<<<<<< HEAD
									dcmObj.getString(Tag.SeriesDescription),
									dcmObj.getString(Tag.Modality),
									dcmObj.getString(Tag.Manufacturer),
									dcmObj.getString(Tag.ManufacturerModelName),
									dcmObj.getDate(Tag.StudyDateAndTime),
									dcmObj.getDate(Tag.StudyDateAndTime), null, null);
							SeriesID = nSeries.getSeriesId();
							seriesMap.put(dcmObj.getString(Tag.SeriesInstanceUID),nSeries);
							ImageSeries imageSeries = new ImageSeries(SeriesID,
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

							List<Images> Images = new ArrayList<Images>();
							Images nImage = new Images(Integer.parseInt(dcmObj
									.getString(Tag.InstanceNumber)),
									dcmObj.getString(Tag.SOPInstanceUID), dcmObj
									.getDouble(Tag.SliceLocation, 0.0),
									Integer.parseInt(dcmObj
											.getString(Tag.InstanceNumber)),
									String.join(",", dcmObj.getStrings(Tag.ImagePositionPatient)), SeriesID);

							session.saveOrUpdate(nImage);
							imageSeries.setSeries(nSeries);
							nSeries.setImageSeries(imageSeries);
							nSeries.setStudy(nStudy);
//                        session.saveOrUpdate(nSeries);
							series.add(nSeries);
							nStudy.setSeries(series);
							nStudy.setPatient(p);
							studies.add(nStudy);
							p.setStudies(studies);
							patientId = (String) session.save(p);
=======
								dcmObj.getString(Tag.SeriesDescription),
								dcmObj.getString(Tag.Modality),
								dcmObj.getString(Tag.Manufacturer),
								dcmObj.getString(Tag.ManufacturerModelName),
								dcmObj.getDate(Tag.StudyDateAndTime),
								dcmObj.getDate(Tag.StudyDateAndTime), null,null);
						SeriesID = nSeries.getSeriesId();

						ImageSeries imageSeries = new ImageSeries(SeriesID,
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
								dcmObj.getInt(Tag.BitsAllocated,0),
								dcmObj.getInt(Tag.BitsStored,0),
								dcmObj.getInt(Tag.HighBit,0),
								dcmObj.getInt(Tag.PixelRepresentation,0),
								dcmObj.getInt(Tag.SmallestImagePixelValue,0),
								dcmObj.getInt(Tag.LargestImagePixelValue,0));

						List<Images> Images = new ArrayList<Images>();
						Images nImage = new Images(Integer.parseInt(dcmObj
								.getString(Tag.InstanceNumber)),
								dcmObj.getString(Tag.SOPInstanceUID), dcmObj
										.getDouble(Tag.SliceLocation, 0.0),
								Integer.parseInt(dcmObj
										.getString(Tag.InstanceNumber)),
								String.join(",", dcmObj.getStrings(Tag.ImagePositionPatient)));


						session.saveOrUpdate(nImage);
                        imageSeries.setSeries(nSeries);
                        nSeries.setImageSeries(imageSeries);
						nSeries.setStudy(nStudy);
						series.add(nSeries);
						nStudy.setSeries(series);
						nStudy.setPatient(p);
						studies.add(nStudy);
						p.setStudies(studies);
						patientId = (String) session.save(p);
>>>>>>> 67cf4694d6b2de79d8554afcb2cc6f819810e0e0
//						System.out.println(patientId);
							tx1.commit();
							is.close();
						} else {

<<<<<<< HEAD
//								InputStream is = file.getInputStream();
//								DicomInputStream dis = new DicomInputStream(is);
//								Attributes dcmObj = dis.readDataset(-1, -1);
							Session session = HibernateUtil.currentSession();
							Transaction tx1 = session.beginTransaction();
							Slice nSlice = new Slice(dcmObj.getString(Tag.SOPInstanceUID),
									Integer.parseInt(dcmObj.getString(Tag.InstanceNumber)),
									dcmObj.getDouble(Tag.SliceLocation, 0.0),
									String.join(",", dcmObj.getStrings(Tag.ImagePositionPatient)),
									new Date(), null, null, null, null);
=======
						InputStream is = file.getInputStream();
						DicomInputStream dis = new DicomInputStream(is);
						Attributes dcmObj = dis.readDataset(-1, -1);
						Session session = HibernateUtil.currentSession();
						Transaction tx1 = session.beginTransaction();
						Images nImage = new Images(Integer.parseInt(dcmObj
								.getString(Tag.InstanceNumber)),
								dcmObj.getString(Tag.SOPInstanceUID), dcmObj
										.getDouble(Tag.SliceLocation, 0.0),
								Integer.parseInt(dcmObj
										.getString(Tag.InstanceNumber)),
								String.join(",", dcmObj.getStrings(Tag.ImagePositionPatient)));
>>>>>>> 67cf4694d6b2de79d8554afcb2cc6f819810e0e0
//						nImage.setSeries(nSeries);
							sliceMap.put(dcmObj.getString(Tag.SOPInstanceUID), nSlice);
							nSlice.setSeries(nSeries);
							session.saveOrUpdate(nSlice);
							tx1.commit();
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
					else if (dcmObj.getString(Tag.Modality).equals("RTSTRUCT") )
					{
						Session session = HibernateUtil.currentSession();
						Transaction tx1 = session.beginTransaction();
						List<StructureSet> structureSet = new ArrayList<StructureSet>();
						StructureSet nStructureSet = new StructureSet(dcmObj.getInt(Tag.SeriesNumber, 0),
								dcmObj.getString(Tag.StructureSetName), new Date(), null, null,dcmObj.getString(Tag.StructureSetDescription),null );
						List<Structure> structures = new ArrayList<Structure>();
						Sequence structureSequence = dcmObj.getSequence(Tag.StructureSetROISequence);
						for(int i=0;i<structureSequence.size();++i ) {
							Structure iStructure = new Structure(Integer.parseInt(structureSequence.get(i).getString(Tag.ROINumber)),nStructureSet.getStructureSetId(), structureSequence.get(i).getString(Tag.ROIName),
									new Date(), null, null, null, null );
							List<Contour> iContours = new ArrayList<Contour>();
							Sequence ROIContourSequence = dcmObj.getSequence(Tag.ROIContourSequence);
							for(int j=0;j<ROIContourSequence.size();++j){
								if (Integer.parseInt(ROIContourSequence.get(j).getString(Tag.ReferencedROINumber))==(iStructure.getROINumber()))
								{
									iStructure.setROIcolor(String.join(",",ROIContourSequence.get(j).getStrings(Tag.ROIDisplayColor)));
									Sequence ContourSequence = ROIContourSequence.get(j).getSequence(Tag.ContourSequence);
									for(int k=0;k<ContourSequence.size();++k)
									{
										Sequence ContourImageSequence = ContourSequence.get(k).getSequence(Tag.ContourImageSequence);
									    Contour kContour = new Contour(ContourImageSequence.get(0).getString(Tag.ReferencedSOPInstanceUID), new Date(), null, null, null,String.join(",", ContourSequence.get(k).getStrings(Tag.ContourData)) );
									if (sliceMap.containsKey(ROIContourSequence.get(j).getString(Tag.ReferencedSOPInstanceUID)))
										kContour.setSlice(sliceMap.get(ROIContourSequence.get(j).getString(Tag.ReferencedSOPInstanceUID)));
									iContours.add(kContour);
									}
									iStructure.setContours(iContours);
									break;
								}
							}
							structures.add(iStructure);
						}
						nStructureSet.setStructures(structures);
						structureSet.add(nStructureSet);
						if(seriesMap.containsKey(dcmObj.getString(Tag.SeriesInstanceUID)))
							seriesMap.get(dcmObj.getString(Tag.SeriesInstanceUID)).setStructureSets(structureSet);
						tx1.commit();
						is.close();
					}

                } catch (Exception e) {
                    return "You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage();
                }

            } else {
                return "You failed to upload " + file.getOriginalFilename() + " because the file was empty.";
            }
        }
        messageQueue.Send("{\"func\": \"imageReady\", \"folderPath\": " +"\"~/data/" + patientId.toString() + "\"" + "}", "1");
        return "You successfully uploaded !";
    }
//	private List<Structure> getStructures(Attributes dcmObj){
//		int roiNumber = dcmObj.getInt(Tag.ROINumber,0);
//		while(){
//			int i = dcmObj.getInt(Tag.AnatomicStructure, 0);
//
//		}
//    }
	private Image Image() {
		// TODO Auto-generated method stub
		return null;
	}
}
