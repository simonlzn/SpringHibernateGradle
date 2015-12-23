package org.sphic.Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap; 

import org.sphic.Model.Patient;
import org.sphic.Service.DAO.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/patient")
public class PatientController {
	private PatientDao patientDao;

	@Autowired
	public PatientController(PatientDao patientDao) {
		this.patientDao = patientDao;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Patient Get(@PathVariable String id) {
		Patient patient = patientDao.get(Patient.class, id);

		if (patient == null) {
			return new Patient();
		}

		return patient;
	}

	/*
	 * The return should be : 
	 * 
{
rows:[
    { id:1001,
     
 data:[
      "100",
      "John Grisham",
      "Cacinorma",
      "Proton",
      "LH",
      "JZ",
      "01/01/2015", 
      "01/02/2015",
      "01/11/2015",
      "01/15/2015",
      "01/16/2015",
      "01/17/2015",
      "02/17/2015",
      "Note from test"
      ] },
      
   { id:1002,
    
 data:[
      "1000",
      "John Grisham",
      "Cacinorma",
      "Proton",
      "LH",
      "JZ",
      "01/01/2015", 
      "01/02/2015",
      "01/11/2015",
      "01/15/2015",
      "01/16/2015",
      "01/17/2015",
      "02/17/2015",
      "Note from test"
      ] }
   ]
}      
	*/
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Map<String, Object> GetPatientList() {
		List<Patient> patientList = patientDao.getAll();
		Map<String, Object> result = new HashMap(); 
		
		List rows = new ArrayList();
		if (patientList.isEmpty()) {
			result.put("rows", rows);
			return result;
		}
		
		Iterator it = patientList.iterator();
		int idIndex = 0; 
		while(it.hasNext())
		{
			Patient p = (Patient)it.next();
			
			List a = new ArrayList(); 
			a.add(p.getUuid());
			//a.add(idIndex); //PatientID 
			a.add(p.getName()); //Patient Name
			a.add(p.getBirthdate()); //birth date value
			a.add("Tumor Name here");  //Turmor Name
			a.add("Doctor Name");  //doctor name here
			a.add("Physists Name"); //Physists names here. 
			
			idIndex++;
			
			Map<String, Object> map = new HashMap();
			map.put("id", p.getUuid());
			map.put("data", a); 
			
			rows.add(map);
		}

		result.put("rows", rows);
		return result;
	}
}