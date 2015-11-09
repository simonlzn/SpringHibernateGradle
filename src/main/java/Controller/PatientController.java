package main.java.Controller;
import javax.transaction.Transactional;

import main.java.HibernateConfig.HibernateUtil;
import main.java.Model.Patient;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class PatientController {
	private SessionFactory sf = HibernateUtil.getSessionFactory();
	private Session session = sf.openSession();
	
    public PatientController() {
	}

	@RequestMapping("/info")
    public Patient Info(@RequestParam(value="id", defaultValue="1") int id) {
		
		Patient patient = (Patient)session.get(Patient.class, id);
		
		
		if (patient == null) {
			return new Patient();			
		}
		
        return patient;
    }
}
