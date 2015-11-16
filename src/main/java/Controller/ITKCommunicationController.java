package main.java.Controller;

import main.java.HibernateConfig.HibernateUtil;
import main.java.Message.MessagingQueue;
import main.java.Model.Patient;

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

