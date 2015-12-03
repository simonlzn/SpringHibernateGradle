package org.sphic.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.sphic.HibernateConfig.HibernateUtil;
import org.sphic.Model.Contour;
import org.sphic.Model.Slice;
import org.sphic.Model.Structure;
import org.sphic.Model.Study;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/contour")
public class ContourController {
	public ContourController() {
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Contour Get(@PathVariable int id) {
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

	@RequestMapping(method = RequestMethod.PUT)
	public int Put(@RequestBody Contour contour, @RequestParam int structureId, @RequestParam int sliceId) {

		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		Structure s = (Structure)session.get(Structure.class, structureId);
		Slice sl = (Slice) session.get(Slice.class, sliceId);
		contour.setStructure(s);
		contour.setSlice(sl);
		Integer c = (Integer) session.save(contour);

		tx.commit();
		return c.intValue();
	}
}
