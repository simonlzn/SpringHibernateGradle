package org.sphic.Controller;

import org.sphic.Model.Series;
import org.sphic.Service.DAO.SeriesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/series")
public class SeriesController {
	private SeriesDao seriesDao;

	@Autowired
	public SeriesController(SeriesDao seriesDao) {
		this.seriesDao = seriesDao;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Series Get(@PathVariable int id) {
		Series series = seriesDao.get(Series.class, id);

		return series;
	}

	@RequestMapping(value = "/{id}/description/{description}", method = RequestMethod.POST)
	public void Post(@PathVariable int id, @PathVariable String description) {
		Series series = seriesDao.get(Series.class, id);

		series.setDescription(description);

		seriesDao.save(series);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public int Put(@RequestBody Series series) {

		return (Integer) seriesDao.save(series);
	}
}
