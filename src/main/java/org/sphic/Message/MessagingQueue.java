package org.sphic.Message;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MessagingQueue {
	private final static String QUEUE_NAME = "queue2";

	public void Send() {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection;
		try {
			connection = factory.newConnection();
			Channel channel = connection.createChannel();

			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			String message = "test";
			channel.basicPublish("", QUEUE_NAME, null,
					message.getBytes("UTF-8"));
			System.out.println(" [x] Sent '" + message + "' to queue 2");

			channel.close();
			connection.close();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (TimeoutException e) {

			e.printStackTrace();
		}
	}

}
