//package org.sphic.Controller;
//
//import org.hibernate.Session;
//import org.hibernate.criterion.Restrictions;
//import HibernateUtil;
//import org.sphic.model.Profile;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/profile")
//public class PatientProfileController {
//	public PatientProfileController() {
//	}
//
//	@RequestMapping("/info")
//	public List<Profile> Info(@RequestParam(value = "id", defaultValue = "1") int id) {
//		Session session = HibernateUtil.currentSession();
//		List<Profile> profiles = session.createCriteria(Profile.class).add(Restrictions.eq("patientId", id)).list();
//
//		return profiles;
//	}
//}
