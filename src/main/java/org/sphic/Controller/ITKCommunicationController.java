package org.sphic.Controller;

import org.sphic.HibernateConfig.HibernateUtil;
import org.sphic.Message.MessagingQueue;
import org.sphic.Model.Patient;

import org.hibernate.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itk")
public class ITKCommunicationController {
	public ITKCommunicationController() {
	}

	@RequestMapping("/call")
	public void Info() {
		MessagingQueue m = new MessagingQueue();
		m.Send();
	}
}

