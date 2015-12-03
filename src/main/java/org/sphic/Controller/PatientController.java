package org.sphic.Controller;

import org.hibernate.Session;
import org.sphic.HibernateConfig.HibernateUtil;
import org.sphic.Model.Patient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {
	public PatientController() {
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Patient Get(@PathVariable String id) {
		Session session = HibernateUtil.currentSession();
		Patient patient = (Patient) session.get(Patient.class, id);

		if (patient == null) {
			return new Patient();
		}

		return patient;
	}
}