package org.sphic.Controller;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.io.DicomInputStream;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sphic.HibernateConfig.HibernateUtil;
import org.sphic.Message.MessageQueue;
import org.sphic.Model.Patient;
import org.sphic.Model.Study;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.*;

@Controller
@Component
@RequestMapping("/import")
public class ImportImageController{
	
	@RequestMapping(value = "/image", method = RequestMethod.POST)
    public @ResponseBody String Upload(@RequestParam("image") MultipartFile[] files) {
        Boolean HasBeenSavedToDatabase = false;
        String patientId =null;
        for (MultipartFile file : files) {

            if (!file.isEmpty()) {
                try {
                    if (!HasBeenSavedToDatabase) {
                        HasBeenSavedToDatabase = true;
                        InputStream is = file.getInputStream();

                        DicomInputStream dis = new DicomInputStream(is);


                        Attributes dcmObj = dis.readDataset(-1, -1);

                        Session session = HibernateUtil.currentSession();
                        Transaction tx1 = session.beginTransaction();
                        Patient p = new Patient(Integer.getInteger(
                                dcmObj.getString(Tag.PatientID), 0),
                                dcmObj.getString(Tag.PatientName), "",
                                dcmObj.getString(Tag.PatientAddress), "",
                                Integer.getInteger(
                                        dcmObj.getString(Tag.PatientAge), 0),null,null);


                        patientId = (String) session.save(p);

                        List<Study> studies = new ArrayList<Study>();
                        studies.add(new Study(Integer.parseInt(dcmObj
                                .getString(Tag.StudyID)), patientId,
                                dcmObj.getString(Tag.StudyInstanceUID),
                                dcmObj.getDate(Tag.StudyDateAndTime),
                                dcmObj.getDate(Tag.StudyDateAndTime), null,null,
                                dcmObj.getString(Tag.StudyDescription), dcmObj.getString(Tag.ReferringPhysicianName),
                                dcmObj.getString(Tag.AccessionNumber), dcmObj.getString(Tag.InstitutionName), null));
                        p.setStudies(studies);
                        System.out.println(patientId);
                        tx1.commit();
                        is.close();
                    }

                    byte[] bytes = file.getBytes();
                    if (patientId == null)
                        throw new Exception("patient ID can not be retrieved");

                    String name = "/home/zshen/data/" + patientId.toString() + "/" + file.getOriginalFilename();

                    File dataFolder = new File("/home/zshen/data/" + patientId.toString() + "/" );
                    if (dataFolder.exists() || dataFolder.mkdirs()) {
                        BufferedOutputStream stream =
                                new BufferedOutputStream(new FileOutputStream(new File(name)));
                        stream.write(bytes);
                        stream.close();

                        System.out.println(name + " is uploaded!");


                    }

                } catch (Exception e) {
                    return "You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage();
                }

            } else {
                return "You failed to upload " + file.getOriginalFilename() + " because the file was empty.";
            }
        }
        return "You successfully imported image !";
    }
	
}
