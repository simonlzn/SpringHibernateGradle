package org.sphic.Message;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
import java.util.UUID;

@Configuration
@ComponentScan("org.sphic")
public class RabbitMQConfig {

    private String queueName = "queue"+ UUID.randomUUID().toString().replace("-","");
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
            new CachingConnectionFactory();
        connectionFactory.setHost("10.11.12.33");
//        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("sphic");
        connectionFactory.setPassword("sphic");
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
//        rabbitAdmin.deleteExchange("java");
        queueName = rabbitAdmin.declareQueue().getName();

        return rabbitAdmin;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        return rabbitTemplate;
    }

    @Bean
    public Binding binding() {
        return new Binding(queueName, Binding.DestinationType.QUEUE, "java", "queue1",null);
    }

    @Bean
    public TopicExchange exchange() {
       return new TopicExchange("itk", false, false);
    }

    @Bean
    public FanoutExchange javaExchange() {
        return new FanoutExchange("java", false, false);
    }


    @Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(MessageQueue messageQueue) {
		return new MessageListenerAdapter(messageQueue, "Recv");
	}
}