package org.sphic.Message;

import org.sphic.util.PubSub;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class MessageQueue {

	private AmqpTemplate template;

	@Autowired
	public MessageQueue(AmqpTemplate template){
		this.template = template;
	}

	public void Send(String message) {
		template.convertAndSend("queue3", message);
	}

	public void Recv(byte[] message) throws UnsupportedEncodingException {
		String messageString = new String(message, "UTF-8");
		System.out.println("Received <" + messageString + ">");
		PubSub.Publish("rabbitmq", messageString);
	}

}
