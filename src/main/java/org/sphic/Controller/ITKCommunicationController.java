package org.sphic.Controller;

import org.sphic.Message.MessageQueue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itk")
public class ITKCommunicationController {
	public ITKCommunicationController() {
	}

	@RequestMapping("/call")

	public void Info(@RequestParam(value = "func", defaultValue = "")String func) {
		MessageQueue m = new MessageQueue();
		m.Send(func);
	}
}

