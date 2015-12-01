package org.sphic.Controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sphic.HibernateConfig.HibernateUtil;
import org.sphic.Model.Contour;
import org.sphic.Model.Structure;
import org.sphic.Model.Study;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contour")
public class ContourController {
	public ContourController() {
	}

	@RequestMapping("/{id}")
	public Contour Info(@PathVariable int id) {
		Session session = HibernateUtil.currentSession();
		Contour contour = (Contour)session.get(Contour.class, id);

		return contour;
	}

	@RequestMapping(value = "/{id}/color/{colorId}", method = RequestMethod.POST)
	public void Post(@PathVariable int id, @PathVariable int colorId) {
		Session session = HibernateUtil.currentSession();
		Contour c = (Contour)session.load(Contour.class, id);

		Transaction tx = session.beginTransaction();
		c.setColorId(colorId);

		session.save(c);
		tx.commit();
	}
}
