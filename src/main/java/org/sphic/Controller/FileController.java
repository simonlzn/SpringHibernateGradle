package org.sphic.Controller;

import javafx.util.Pair;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.io.DicomInputStream;
import org.sphic.Message.MessageQueue;
import org.sphic.Model.Series;
import org.sphic.Model.Slice;
import org.sphic.Service.ContourService;
import org.sphic.Service.DAO.SeriesDao;
import org.sphic.Service.ImageExtractService;
import org.sphic.Service.SliceService;
import org.sphic.Service.StructureExtractService;
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
import java.util.*;

@Controller
@Component
@RequestMapping("/file")
public class FileController {
    private final MessageQueue messageQueue;
    private SliceService sliceService;
    private ContourService contourService;
    private StructureExtractService structureExtractService;
    private ImageExtractService imageExtractService;

    @Autowired
    public FileController(MessageQueue messageQueue, SliceService sliceService, ContourService contourService, StructureExtractService structureExtractService,
                          ImageExtractService imageExtractService) {
        this.messageQueue = messageQueue;
        this.sliceService = sliceService;
        this.contourService = contourService;
        this.structureExtractService = structureExtractService;
        this.imageExtractService = imageExtractService;
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public
    @ResponseBody
    String download(final HttpServletResponse response) throws InterruptedException {
//		sliceService.SortAndUpdateSlices(107205);
//        String message = contourService.ConstructDataByStructureId(29);
//        System.out.println(new Date().getTime());
//		messageQueue.Send("{\"func\": \"contourReconstruct\", \"id\": \"1\",\"key\": \"test\",\"contours\": " +"\"" + "" + "\"" + "}", "1");
        Thread.sleep(5000);
        return "";//message;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public
    @ResponseBody
    String Upload(@RequestParam("file") MultipartFile[] files) {
        Map<String, Slice> sliceMap = new HashMap<String, Slice>();
        Map<Pair<String,String>, Series> seriesMap = new HashMap<Pair<String,String>, Series>();
        List<MultipartFile> imageFiles = new ArrayList<MultipartFile>();
        List<MultipartFile> structureFiles = new ArrayList<MultipartFile>();
        int SeriesId = 0;
        for (MultipartFile file : files) {

            if (!file.isEmpty()) {
                try {
                    InputStream is = file.getInputStream();
                    DicomInputStream dis = new DicomInputStream(is);
                    Attributes dcmObj = dis.readDataset(-1, -1);
                    if (dcmObj.getString(Tag.Modality).equals("CT"))
                        imageFiles.add(file);
                    else if (dcmObj.getString(Tag.Modality).equals("RTSTRUCT"))
                        structureFiles.add(file);
                } catch (Exception e) {
                    return "You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage();
                }

			} else {
				return "You failed to upload " + file.getOriginalFilename() + " because the file was empty.";
			}
		}
		boolean bWritePatient = true;
		for (MultipartFile file : imageFiles) {

			if (!file.isEmpty()) {
				try {
					if(bWritePatient) {
                        SeriesId = imageExtractService.writePatient(file, seriesMap);
						bWritePatient = false;
					}
					imageExtractService.writeImage(file, sliceMap);
                } catch (Exception e) {
                    return "You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage();
                }

            } else {
                return "You failed to upload " + file.getOriginalFilename() + " because the file was empty.";
            }
        }
        for (MultipartFile file : structureFiles) {
            if (!file.isEmpty()) {
                try {
                    structureExtractService.writeStructure(file, sliceMap, seriesMap);
                } catch (Exception e) {
                    return "You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage();
                }
            } else {
                return "You failed to upload " + file.getOriginalFilename() + " because the file was empty.";
            }

            sliceService.SortAndUpdateSlices(SeriesId);
        }
//        messageQueue.Send("{\"func\": \"imageReady\", \"folderPath\": " +"\"~/data/" + patientId.toString() + "\"" + "}", "1");

        return "You successfully uploaded DICOM files!";
    }
}
