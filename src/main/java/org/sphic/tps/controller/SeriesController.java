package org.sphic.tps.controller;

import org.sphic.tps.model.Series;
import org.sphic.tps.model.StructureSet;
import org.sphic.tps.service.DAO.SeriesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/series")
public class SeriesController {
	private SeriesDAO seriesDAO;

	@Autowired
	public SeriesController(SeriesDAO seriesDAO) {
		this.seriesDAO = seriesDAO;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Series Get(@PathVariable int id) {
		Series series = seriesDAO.findById(id);

		return series;
	}

	@RequestMapping(value = "/{id}/structureSets", method = RequestMethod.GET)
	public Set<StructureSet> GetStructureSets(@PathVariable int id) {
		Series series = seriesDAO.findById(id);

		return series.getStructureSets();
	}

	@RequestMapping(value = "/{id}/description/{description}", method = RequestMethod.POST)
	public void Post(@PathVariable int id, @PathVariable String description) {
		Series series = seriesDAO.findById(id);

		series.setDescription(description);

		seriesDAO.save(series);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void Put(@RequestBody Series series) {
		seriesDAO.save(series);
	}
}
