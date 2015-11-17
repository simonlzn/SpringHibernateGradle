package org.sphic.Message;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MessageQueue {
	
	private ApplicationContext context = new AnnotationConfigApplicationContext(
			RabbitMQConfig.class);
	private AmqpTemplate template = context.getBean(AmqpTemplate.class);

	public void Send(String message) {
		template.convertAndSend(RabbitMQConfig.QUEUE_NAME, message);
	}

	public String Recv() {
		String ret = (String) template.receiveAndConvert(RabbitMQConfig.QUEUE_NAME);
		System.out.println("message " + ret + " received");
		return ret;
	}

}
