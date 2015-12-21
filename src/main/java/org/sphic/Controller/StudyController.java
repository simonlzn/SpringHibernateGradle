package org.sphic.Controller;

import org.sphic.Model.Study;
import org.sphic.Service.DAO.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/study")
public class StudyController {
	private Dao dao;

	@Autowired
	public StudyController(Dao dao) {
		this.dao = dao;
	}

	@RequestMapping("/{id}")
	public Study Get(@PathVariable int id) {
		return dao.get(Study.class, id);
	}
}
