package org.sphic.Controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sphic.HibernateConfig.HibernateUtil;
import org.sphic.Model.Series;
import org.sphic.Model.StructureSet;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/structureset")
public class StructureSetController {
	public StructureSetController() {
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public StructureSet Get(@PathVariable int id) {
		Session session = HibernateUtil.currentSession();
		StructureSet structureSet = (StructureSet) session.get(StructureSet.class, id);

		return structureSet;
	}

	@RequestMapping(value = "/{id}/description/{description}", method = RequestMethod.POST)
	public void Post(@PathVariable int id, @PathVariable String description) {
		Session session = HibernateUtil.currentSession();
		StructureSet s = (StructureSet)session.load(StructureSet.class, id);

		Transaction tx = session.beginTransaction();
		s.setDescription(description);

		session.save(s);
		tx.commit();
	}

	@RequestMapping(method = RequestMethod.PUT)
	public int Put(@RequestBody StructureSet structureSet) {

		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		Integer s = (Integer) session.save(structureSet);

		tx.commit();
		return s.intValue();
	}
}
