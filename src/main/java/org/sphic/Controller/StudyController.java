package org.sphic.Controller;

import org.hibernate.Session;
import org.sphic.HibernateConfig.HibernateUtil;
import org.sphic.Model.Study;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/study")
public class StudyController {
	public StudyController() {
	}

	@RequestMapping("/{id}")
	public Study Get(@PathVariable int id) {
		Session session = HibernateUtil.currentSession();
		Study study = (Study)session.get(Study.class, id);	

		return study;
	}
}
