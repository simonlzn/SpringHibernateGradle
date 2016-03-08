package org.sphic.tps.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.sphic.tps.util.PubSub;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

@Component
public class MessageQueue {

	private AmqpTemplate template;

	@Autowired
	public MessageQueue(AmqpTemplate template){
		this.template = template;
	}

	public void Send(String message, String routingKey) {
		template.convertAndSend("itk",routingKey, message);
	}

	public void Recv(byte[] message) throws UnsupportedEncodingException {
		String messageString = new String(message, "UTF-8");
		System.out.println("Received <" + messageString + ">");
		System.out.println(new Date().getTime());
		try {
			Map<String, Object> userData = new ObjectMapper().readValue(messageString, Map.class);
			PubSub.Publish(userData.get("key").toString(), userData.get("info"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

