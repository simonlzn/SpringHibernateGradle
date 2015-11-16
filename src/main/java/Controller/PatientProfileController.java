package main.java.Controller;

import java.util.List;

import main.java.HibernateConfig.HibernateUtil;
import main.java.Model.Account;
import main.java.Model.Patient;
import main.java.Model.Profile;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class PatientProfileController {
	public PatientProfileController() {
	}

	@RequestMapping("/info")
	public List<Profile> Info(@RequestParam(value = "id", defaultValue = "1") int id) {
		Session session = HibernateUtil.currentSession();
		List<Profile> profiles = session.createCriteria(Profile.class).add(Restrictions.eq("patientId", id)).list();

		return profiles;
	}
}
