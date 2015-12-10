package org.sphic.Controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sphic.HibernateConfig.HibernateUtil;
import org.sphic.Model.Contour;
import org.sphic.Model.Series;
import org.sphic.Model.Slice;
import org.sphic.Model.Structure;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/series")
public class SeriesController {
	public SeriesController() {
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Series Get(@PathVariable int id) {
		Session session = HibernateUtil.currentSession();
		Series series = (Series) session.get(Series.class, id);

		return series;
	}

	@RequestMapping(value = "/{id}/description/{description}", method = RequestMethod.POST)
	public void Post(@PathVariable int id, @PathVariable String description) {
		Session session = HibernateUtil.currentSession();
		Series s = (Series)session.load(Series.class, id);

		Transaction tx = session.beginTransaction();
		s.setDescription(description);

		session.save(s);
		tx.commit();
	}

	@RequestMapping(method = RequestMethod.PUT)
	public int Put(@RequestBody Series series) {

		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		Integer s = (Integer) session.save(series);

		tx.commit();
		return s.intValue();
	}
}
