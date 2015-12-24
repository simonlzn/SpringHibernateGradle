package org.sphic.Controller;

import org.sphic.Service.ImageSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/imageseries")
public class ImagesSeriesController {
	private ImageSeriesService imageSeriesService;

	@Autowired
	public ImagesSeriesController(ImageSeriesService imageSeriesService) {
		this.imageSeriesService = imageSeriesService;
	}

	@RequestMapping(value = "/{id}/volume", method = RequestMethod.GET)
	public String GetVolume(@PathVariable int id) {
		String volume  = imageSeriesService.GetVolume(id);

		return volume;
	}
}
