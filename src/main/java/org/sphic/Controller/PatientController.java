package org.sphic.Controller;

import org.sphic.Model.Patient;
import org.sphic.Service.DAO.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Patient> GetPatientList() {
		List<Patient> patientList = patientDao.getAll();

		if (patientList.isEmpty()) {
			return new ArrayList<Patient>();
		}

		return patientList;
	}
}