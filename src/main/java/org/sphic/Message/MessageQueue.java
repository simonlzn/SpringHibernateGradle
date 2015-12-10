package org.sphic.Message;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.parser.JSONParser;
import org.sphic.util.PubSub;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
		try {
			Map<String,String> userData = new ObjectMapper().readValue(messageString, Map.class);
			System.out.println(userData.get("key"));
			PubSub.Publish(userData.get("key"), userData.get("info"));
		} catch (IOException e) {
			e.printStackTrace();
		}



	}

}
