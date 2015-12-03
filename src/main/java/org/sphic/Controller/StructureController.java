package org.sphic.Controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sphic.HibernateConfig.HibernateUtil;
import org.sphic.Model.Structure;
import org.sphic.Model.StructureSet;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/structure")
public class StructureController {
	public StructureController() {
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Structure Get(@PathVariable int id) {
		Session session = HibernateUtil.currentSession();
		Structure structure = (Structure) session.get(Structure.class, id);

		return structure;
	}

	@RequestMapping(value = "/{id}/description/{description}", method = RequestMethod.POST)
	public void Post(@PathVariable int id, @PathVariable String description) {
		Session session = HibernateUtil.currentSession();
		Structure s = (Structure)session.load(Structure.class, id);

		Transaction tx = session.beginTransaction();
		s.setDescription(description);

		session.save(s);
		tx.commit();
	}

	@RequestMapping(method = RequestMethod.PUT)
	public int Put(@RequestBody Structure structure) {

		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		Integer s = (Integer) session.save(structure);

		tx.commit();
		return s.intValue();
	}
}
