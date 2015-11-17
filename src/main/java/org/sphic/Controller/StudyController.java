package org.sphic.Controller;

import org.sphic.HibernateConfig.HibernateUtil;
import org.sphic.Model.Study;

import org.hibernate.Session;
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
