package main.java.Controller;

import java.util.List;

import main.java.HibernateConfig.HibernateUtil;
import main.java.Model.Account;
import main.java.Model.Patient;
import main.java.Model.Series;
import main.java.Model.Study;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/study")
public class StudyController {
	public StudyController() {
	}

	@RequestMapping("/info")
	public Study Info(@RequestParam(value = "studyId", defaultValue = "3") int id) {
		Session session = HibernateUtil.currentSession();
		Study study = (Study)session.get(Study.class, id);	

		return study;
	}
}
