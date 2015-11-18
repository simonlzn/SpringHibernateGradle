package org.sphic.Message;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MessageQueue {

	private ConnectionFactory connectionFactory = new CachingConnectionFactory();

	private AmqpAdmin admin = new RabbitAdmin(connectionFactory);
	private AmqpTemplate template = new RabbitTemplate(connectionFactory);
	private ApplicationContext context = new AnnotationConfigApplicationContext(
			RabbitMQConfig.class);
//	private AmqpTemplate template = context.getBean(AmqpTemplate.class);

	public MessageQueue () {
//		admin.declareQueue(new Queue("queue1"));
//		admin.declareQueue(new Queue("queue3"));
	}
	public void Send(String message) {
		template.convertAndSend("queue3", message);
	}

	public String Recv() {
		String ret = (String) template
				.receiveAndConvert(RabbitMQConfig.QUEUE_NAME);
		System.out.println("message " + ret + " received");
		return ret;
	}

}
